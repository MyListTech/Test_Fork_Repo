package test;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import model.Blog;
import model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import hibernate.HibernateUtils;

/**
 * 2011年4月10日 上午10:11:35
 */
public class SearchTest extends TestCase {

	/**
	 * 保存/修改/删除的时候会自动索引
	 */
	public void testIndexing() {
		User user = new User();
		user.setUsername("pugwoo");
		HibernateUtils.save(user);
		
		Blog blog = new Blog();
		blog.setAuthor(user);
		blog.setTitle("a blog");
		blog.setPublishDate(new Date());
		blog.setContent("Hello World, This is the first blog. 中文分词测试，这里是中山大学");

		HibernateUtils.save(blog);
		HibernateUtils.commit();

		/**
		 * 测试HQL的操作是否会同步更新，答案是【不会】
		 */
		//HibernateUtils.execute("update Blog set content='Canton Teochew'");
		//HibernateUtils.commit();

	}

	/**
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	public void testSearching() {
		Session session = HibernateUtils.getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		Transaction tx = fullTextSession.beginTransaction();

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Blog.class).get();

		// QueryBuilder构建好查询语句后，就交给Lucene查询
		org.apache.lucene.search.Query query = queryBuilder.keyword().onFields(
				"title", "content").matching("中山").createQuery();

		// wrap Lucene query in a org.hibernate.Query
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
				query, Blog.class);

		// execute search
		List result = hibQuery.list();

		// 输出result
		System.out.println("total:" + result.size());
		for (int i = 0; i < result.size(); i++) {
			Blog blog = (Blog) result.get(i);
			System.out.println(i);
			System.out.println(blog.getTitle());
			System.out.println(blog.getContent());
		}

		tx.commit();
		session.close();
	}

	/**
	 * 重建数据库中所有数据的索引
	 */
	public void testIndexAll() throws InterruptedException {
		Session session = HibernateUtils.getSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		fullTextSession.createIndexer().startAndWait();
	}
}
