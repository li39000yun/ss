package sy.model.account;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 账户
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Account implements java.io.Serializable {

	// Fields

	private String id;
	private String name;// 账户名
	private Double money;// 余额
	private Date createdatetime;// 创建时间
	private String remark;// 备注
	private int type;// 类型(0:现金;1:银行卡;2:虚拟账户;3:信用卡)
	private int actflag;// 状态(0:禁用;1:启用;)
	

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Account(String id, String name, Double money, Timestamp createdatetime, String remark, Short type) {
		this.id = id;
		this.name = name;
		this.money = money;
		this.createdatetime = createdatetime;
		this.remark = remark;
		this.type = type;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "money", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "createdatetime", length = 0)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "type")
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "actflag")
	public int getActflag() {
		return actflag;
	}

	public void setActflag(int actflag) {
		this.actflag = actflag;
	}

}