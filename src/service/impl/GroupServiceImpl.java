package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import enums.GroupType;
import enums.Visibility;
import mapper.GroupMapper;
import pojo.Article;
import pojo.ArticleFavority;
import pojo.Group;
import pojo.User;
import pojo.UserFollow;
import service.ArticleFavorityService;
import service.ArticleService;
import service.GroupService;
import service.UserFollowService;
import service.UserService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupMapper groupMapper;

	@Autowired
	ArticleService articleService;
	@Autowired
	UserService userService;

	@Autowired
	ArticleFavorityService articleFavorityService;
	@Autowired
	UserFollowService userFollowService;

	@Override
	public Integer insert(Group t) {
		groupMapper.insert(t);
		return t.getId();
	}

	@Override
	public void delete(Integer id) {
		groupMapper.delete(id);
	}

	@Override
	public void update(Group t) {
		groupMapper.update(t);
	}

	@Override
	public Group get(Integer id) {
		return groupMapper.get(id);
	}

	@Override
	public List<Group> list() {
		return groupMapper.list();
	}

	@Override
	public Integer count() {
		return groupMapper.count();
	}

	@Override
	public void delete2(Group group) {
		groupMapper.delete2(group);
	}

	@Override
	public List<Integer> batchInsert(List<Group> groups) {
		List<Integer> ids = new ArrayList<>();
		groupMapper.batchInsert(groups);
		for (Group group : groups) {
			ids.add(group.getId());
		}
		return ids;
	}

	@Override
	public void batchDelete(List<Integer> ids) {
		groupMapper.batchDelete(ids);
	}

	@Override
	public void dynamicInsert(Group group) {
		groupMapper.dynamicInsert(group);
	}

	@Override
	public void dynamicUpdate(Group group) {
		groupMapper.dynamicUpdate(group);
	}

	@Override
	public Group getByUserNameAndGroupType(Integer uid, String name, GroupType groupType) {
		return groupMapper.getByUserNameAndGroupType(uid, name, groupType);
	}

	@Override
	public List<Group> listByUser(Integer uid) {
		return groupMapper.listByUser(uid);
	}

	/* 对于自身用户 */
	@Override
	public List<Group> listByUserAndGroupType(Integer uid, GroupType groupType) {
		// List<Group> gs = groupMapper.listByUserAndGroupType(uid, groupType);
		// setGroups(gs);
		return groupMapper.listByUserAndGroupType(uid, groupType);
	}

	/* 对于自身用户或其他用户 */
	@Override
	public List<Group> listByUserGroupTypeAndVisibility(Integer uid, GroupType groupType, Visibility visibility) {
		// List<Group> gs = groupMapper.listByUserGroupTypeAndVisibility(uid, groupType,
		// visibility);
		// setGroups(gs);
		return groupMapper.listByUserGroupTypeAndVisibility(uid, groupType, visibility);
	}

	@Override
	public List<Integer> listGroupIdByUser(Integer uid) {
		return groupMapper.listGroupIdByUser(uid);
	}

	@Override
	public List<Integer> listGroupId(Group group) {
		return groupMapper.listGroupId(group);
	}

	@Override
	public Integer insertDefVisibleGroupForUserAndGroupType(Integer uid, GroupType groupType) {
		Group group = new Group();
		group.setName("默认");
		group.setUid(uid);
		group.setGroupType(groupType);
		group.setVisibility(Visibility.VISIBLE);
		groupMapper.insert(group);
		return group.getId();
	}

	public void setGroups(List<Group> groups) {
		for (Group group : groups) {
			setGroup(group);
		}
	}

	public void setGroup(Group group) {
		Integer gid = group.getId();
		List<Article> articles = new ArrayList<>();
		List<User> users = new ArrayList<>();
		switch (group.getGroupType()) {
		case ARTICLE_FAVORITY:
			List<ArticleFavority> afs = articleFavorityService.listByGroup(gid);
			for (ArticleFavority af : afs) {
				articles.add(articleService.get(af.getAid()));
			}
			break;
		case USER_FOLLOW:
			List<UserFollow> ufs = userFollowService.listByGroup(gid);
			for (UserFollow uf : ufs) {
				users.add(userService.get(uf.getUid()));
			}
			break;
		default:
			break;
		}
		group.setArticles(articles);
		group.setUsers(users);
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/applicationContext.xml");
		GroupService service = context.getBean(GroupService.class);
		Group group = new Group("娱乐", Visibility.VISIBLE, GroupType.ARTICLE_FAVORITY, 1);
		System.out.println(service.listGroupId(group));
		System.out.println(service.listByUser(1));
		// service.dynamicInsert(group);

		List<Group> visibleGroups = service.listByUserGroupTypeAndVisibility(1, GroupType.ARTICLE_FAVORITY,
				Visibility.VISIBLE);
		System.out.println(visibleGroups);
	}

}
