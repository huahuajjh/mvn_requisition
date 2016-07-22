package com.tq.requisition.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.tq.requisition.domain.model.role.Role;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;

public class TestJson {
	@Test
	public void toJson() {
		Role role = new Role();
		role.setDel(true);
		role.setId(UUID.randomUUID());
		role.setRoleName("rolename");
		String json = Serialization.toJson(role);
		System.out.println(json);
	}
	
	@Test
	public void toJsonArray() {
		List<Role> list = new ArrayList<Role>();
		for (int i = 0; i < 10; i++) {
			Role role = new Role();
			role.setDel(true);
			role.setId(UUID.randomUUID());
			role.setRoleName("rolename");
			
			list.add(role);
		}
		String jsonString = Serialization.toJson(list);
		System.out.println(jsonString);
	}
	
	@Test
	public void toObject() {
//		String role = "{'roleName':'jjh','del':true,'id':'ad638892-5ea9-4be3-be8d-5d72613ed440'}";
//		Role r = Serialization.toObject(role, Role.class);
//		System.out.println(r.getRoleName());
		String string = "{\"headName\":\"1\",\"streetId\":\"7c7a8692-914f-44cc-b4c2-1b31971e61a4\",\"communityId\":\"72b670d8-6369-41f0-b784-74f745f9f98e\",\"address\":\"a1a-s2\",\"fmlNumber\":2,\"houseLegalArea\":1,\"houseIllegalArea\":1,\"satuationDesc\":\"3\",\"dealSolution\":\"2\",\"unionSuggestion\":\"4\",\"remark\":\"5\",\"items\":[{\"name\":\"1\",\"relationshipStr\":\"1\",\"idNumber\":\"2\",\"birthday\":\"2015/12/15\",\"gender\":\"0\",\"householdStr\":\"1\",\"onlyChildNumber\":\"21\",\"half\":true,\"streetId\":\"7c7a8692-914f-44cc-b4c2-1b31971e61a4\",\"communityId\":\"72b670d8-6369-41f0-b784-74f745f9f98e\",\"address\":\"a1a-s2\"},{\"name\":\"2\",\"relationshipStr\":\"2\",\"idNumber\":\"1\",\"birthday\":\"2015/12/09\",\"gender\":\"0\",\"householdStr\":\"1\",\"onlyChildNumber\":\"2\",\"half\":false,\"streetId\":\"7c7a8692-914f-44cc-b4c2-1b31971e61a4\",\"communityId\":\"72b670d8-6369-41f0-b784-74f745f9f98e\",\"address\":\"a1a-s2\"}]}";
		FamilyDto f = Serialization.toObject(string, FamilyDto.class);
		for (FamilyItemDto item : f.getItems()) {
			System.out.println(item.toString());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void toObjList() {
		String lisString = "[{'roleName':'rolename','del':true,'id':'815fc976-6fcf-4a72-a4a6-e3e3c4dfc77e'},{'roleName':'rolename','del':true,'id':'b4be3766-2298-449c-9a55-de0264adfee5'},{'roleName':'rolename','del':true,'id':'09b3e1b2-d619-4e69-b1cd-a3c7af76cd84'},{'roleName':'rolename','del':true,'id':'42c8c88c-dc54-4a56-9ce6-7b5fa8dd1a5a'},{'roleName':'rolename','del':true,'id':'416c780e-d892-4cc9-a8e7-6eadaaeb1fae'},{'roleName':'rolename','del':true,'id':'1e7ffaad-efbe-43f4-bfb0-f0324c1b8e6c'},{'roleName':'rolename','del':true,'id':'7a2e5bab-c0c0-44aa-b21c-f8b2ee798235'},{'roleName':'rolename','del':true,'id':'49391432-8e36-4660-8dfc-3783e95e5ddb'},{'roleName':'rolename','del':true,'id':'b79c3b69-c3b4-4e9d-b29a-b7faf9295750'},{'roleName':'rolename','del':true,'id':'f9ea398a-c544-46a7-b996-a4f3ecea4ec2'}]";
		List list = Serialization.toList(lisString, List.class);
		for (Object object : list) {
			System.out.println(object.toString());
		}
		
	}
	
	@Test
	public void format() {		
		String str = "select count(1) from tb_account where name like '%%' and account like '%%' and org_id like '%%' and dept_id like '%%'";
		System.out.println(str);
	}

	@Test
	public void array() {
//		List<Object> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		Object[] objects = new Object[list.size()];
//		for (int i = 0; i < objects.length; i++) {
//			objects[i] = list.get(i);
//		}
//		for (int i = 0; i < objects.length; i++) {
//			System.out.println(objects[i]);
//		}
		
//		for (int i = 0; i < 17; i++) {
//			System.out.println(UUID.randomUUID().toString());
//		}
		
//		System.out.println(DateFormat.getDateInstance().format(new Date("2015-02-02".replace("-", "/"))));
//		System.out.println(new Date().getTime());
	}
}
