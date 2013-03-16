package com.pugwoo.quartz;

import java.io.Serializable;

/**
 * 2013年3月16日 13:06:40
 * @author pugwoo
 * 序列化是为了数据库持久化
 */
public interface SerializableJob extends org.quartz.Job, Serializable {

}
