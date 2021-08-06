package com.example.testtaskdixy.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubdivisionService {

	public List<String> sortSubdivisions(List<String> subdivisionList) {
		return subdivisionList.stream()
				.sorted((o1, o2) -> {
					String[] list1 = o1.split("\\\\");
					String[] list2 = o2.split("\\\\");
					if (!list1[0].equals(list2[0])) return o2.compareTo(o1);
					if (list1.length > list2.length) return 1;
					if (list1.length < list2.length) return -1;
					return o2.compareTo(o1);
				})
				.collect(Collectors.toList());
	}

	public List<String> addMissingSubdivision(List<String> list1) {
		LinkedList<String> list = new LinkedList<>(list1);
		for (int i = 0; i < list.size() - 1; i++) {
			String[] currentArr = list.get(i).split("\\\\");

			//at the beginning
			if (i == 0 && currentArr.length > 0) {
				int itemToAdd1 = currentArr.length - 1;
				increaseList(list, itemToAdd1, currentArr, 0);
			}
			String[] nextArr = list.get(i + 1).split("\\\\");

			//to change 1 group
			if (!currentArr[0].equals(nextArr[0])) {
				int itemToAdd1 = nextArr.length - 1;
				increaseList(list, itemToAdd1, nextArr, i + 1);
			}

			//in between
			int itemToAdd = nextArr.length - currentArr.length - 1;
			while(itemToAdd > 0) {
				StringBuilder strToAdd = new StringBuilder(list.get(i));
				for (int j = 0; j < itemToAdd; j++) {
					strToAdd.append("\\");
					strToAdd.append(nextArr[j + 1]);
				}
				list.add(i + 1, strToAdd.toString());
				itemToAdd--;
			}
		}
		return list;
	}

	private void increaseList(LinkedList<String> list, int itemToAdd, String[] arr2, int place) {
		while (itemToAdd > 0) {
			StringBuilder strToAdd = new StringBuilder();
			for (int j = 0; j < itemToAdd; j++) {
				if (j >= 1) {
					strToAdd.append("\\");
				}
				strToAdd.append(arr2[j]);
			}
			list.add(place, strToAdd.toString());
			itemToAdd--;
		}
	}

	public void showList(List<String> list, String description) {
		System.out.println("-----------" + description + "-------------");
		list.forEach(System.out::println);
		System.out.println("-------------------------------");
	}

}
