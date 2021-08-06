package com.example.testtaskdixy.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SubdivisionServiceTest {

	@Autowired
	private SubdivisionService subdivisionService;
	private List<String> subdivisionTestList;

	{
		subdivisionTestList = Arrays.asList(
				"K1\\SK1",
				"K1\\SK2",
				"K1\\SK1\\SSK1",
				"K1\\SK1\\SSK2",
				"K2",
				"K2\\SK1\\SSK1",
				"K2\\SK1\\SSK2"
		);
	}

	@Test
	public void testFromTask() {
		subdivisionService.showList(subdivisionTestList, "Input");

		List<String> sortedList = subdivisionService.sortSubdivisions(subdivisionTestList);
		List<String> result = subdivisionService.addMissingSubdivision(sortedList);

		List<String> expectedList = Arrays.asList(
				"K2",
				"K2\\SK1",
				"K2\\SK1\\SSK2",
				"K2\\SK1\\SSK1",
				"K1",
				"K1\\SK2",
				"K1\\SK1",
				"K1\\SK1\\SSK2",
				"K1\\SK1\\SSK1"
		);
		subdivisionService.showList(result, "Output");

		Assertions.assertArrayEquals(expectedList.toArray(), result.toArray());
	}

	@Test
	public void additionalTest() {
		subdivisionTestList = Arrays.asList(
				"K2\\SK1\\SSK1",
				"K1\\SK1\\SSK1",
				"K1\\SK1",
				"K2\\SK1\\SSK2",
				"K3\\SK1\\SSK1"
		);

		List<String> sortedList = subdivisionService.sortSubdivisions(subdivisionTestList);
		List<String> result = subdivisionService.addMissingSubdivision(sortedList);

		List<String> expectedList = Arrays.asList(
				"K3",
				"K3\\SK1",
				"K3\\SK1\\SSK1",
				"K2",
				"K2\\SK1",
				"K2\\SK1\\SSK2",
				"K2\\SK1\\SSK1",
				"K1",
				"K1\\SK1",
				"K1\\SK1\\SSK1"
		);
		Assertions.assertArrayEquals(expectedList.toArray(), result.toArray());
	}
}