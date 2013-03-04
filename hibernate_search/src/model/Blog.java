package model;

import java.util.Date;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

/**
 * @Index指示索引的类
 * @Analyzer指示分词器，默认是Lucene自带的
 */
@Indexed
@Analyzer(impl = net.paoding.analysis.analyzer.PaodingAnalyzer.class)
public class Blog {

	/**
	 * 必须有一个DocumentId
	 */
	@DocumentId
	private Long id;

	/**
	 * 若干个Field，其中可配置 ：store : 默认不保存； index : 配置是否分词
	 */
	@Field(store = Store.YES, index = Index.TOKENIZED)
	private String title;

	/**
	 * Hibernate建立了各种Bridge，因为Lucene只能索引String，所以需要把Object转换成String
	 * 像DateBridge的Resolution(分辨率)指的是精确到多少
	 * Bridge可能在searching的时候有【问题】
	 */
	@Field(index = Index.UN_TOKENIZED, store = Store.YES)
	@DateBridge(resolution = Resolution.SECOND)
	private Date publishDate;
	
	/**
	 * 演示自定义Bridge
	 * 更多：http://docs.jboss.org/hibernate/search/3.3/reference/en-US/html/search-mapping.html#search-mapping-bridge
	 */
	@Field(index = Index.UN_TOKENIZED, store = Store.YES)
	@FieldBridge(impl = bridge.UserBridge.class)
	private User author;

	@Field(store = Store.NO, index = Index.TOKENIZED)
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
