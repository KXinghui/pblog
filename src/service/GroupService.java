package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import enums.GroupType;
import enums.Visibility;
import pojo.Group;

public interface GroupService extends BaseService<Group> {
	void delete2(Group group);
	/* 批量操作 */

	List<Integer> batchInsert(List<Group> groups);

	void batchDelete(List<Integer> ids);

	// void batchUpdate(List<Group> groups);

	/* 动态操作 */

	void dynamicInsert(Group group);

	void dynamicUpdate(Group group);

	/* 动态批量操作 */

	// void dynamicBatchInsert(List<Group> groups);

	// void dynamicBatchUpdate(List<Group> groups);

	/* 查询获取 */

	Group getByUserNameAndGroupType(Integer uid, String name, GroupType groupType);

	List<Group> listByUser(Integer uid);

	List<Group> listByUserAndGroupType(Integer uid, GroupType groupType);

	List<Group> listByUserGroupTypeAndVisibility(Integer uid, GroupType groupType, Visibility visibility);

	List<Integer> listGroupIdByUser(Integer uid);

	/* 为用户创建指定分组类型的默认显示分组 */
	Integer insertDefVisibleGroupForUserAndGroupType(Integer uid, GroupType groupType);

	List<Integer> listGroupId(Group group);
}
