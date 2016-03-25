package sy.model.oa;

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
 * 员工
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Employee implements java.io.Serializable {

	// Fields

	private String id;
	private String name;// 姓名
	private Integer age;// 年龄
	private Date createdatetime;// 创建时间
	private Date updatedatetime;// 修改时间
	private String sex;// 性别
	private String remark;// 备注

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String id) {
		this.id = id;
	}

	/** full constructor */
	public Employee(String id, String name, Integer age, Date createdatetime, Date updatedatetime, String sex, String remark) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.createdatetime = createdatetime;
		this.updatedatetime = updatedatetime;
		this.sex = sex;
		this.remark = remark;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "createdatetime", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "updatedatetime", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}