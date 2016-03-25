package sy.action.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.BaseAction;
import sy.model.account.Account;
import sy.model.easyui.Json;
import sy.service.account.AccountServiceI;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 账户
 * 
 * action访问地址是/account/account.sy
 * 
 * @author lyq
 * 
 */
@Namespace("/account")
@Action
public class AccountAction extends BaseAction<Account> {

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(AccountServiceI service) {
		this.service = service;
	}

	/**
	 * 新建一个账户
	 */
	synchronized public void save() {
		Json json = new Json();
		if (data != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#name_S_EQ", data.getName());
			Account Account = service.getByFilter(hqlFilter);
			if (Account != null) {
				json.setMsg("新建账户失败，账户名已存在！");
			} else {
				service.save(data);
				json.setMsg("新建账户成功！");
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 更新一个账户
	 */
	synchronized public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		if (data != null && !StringUtils.isBlank(data.getId())) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#id_S_NE", data.getId());
			hqlFilter.addFilter("QUERY_t#name_S_EQ", data.getName());
			Account Account = service.getByFilter(hqlFilter);
			if (Account != null) {
				json.setMsg("更新账户失败，账户名已存在！");
			} else {
				Account t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
		}
		writeJson(json);
	}

}
