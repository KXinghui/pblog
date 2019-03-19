package pojo;

import java.time.LocalDateTime;

public class ArticleHistory {
	private Integer id;
	private Integer uid;
	private Integer aid;
	private LocalDateTime createTime;

	/* 非数据库字段 */
	private User author;
	private Article article;

	public ArticleHistory() {
	}

	public ArticleHistory(Integer id, Integer uid, Integer aid) {
		this.id = id;
		this.uid = uid;
		this.aid = aid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleHistory [id=" + id + ", uid=" + uid + ", aid=" + aid + ", createTime=" + createTime + ", author="
				+ author + ", article=" + article + "]";
	}

}
