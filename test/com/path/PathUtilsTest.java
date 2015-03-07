package com.path;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PathUtilsTest {
	ReadFile rdfile = new ReadFile();
	String content = rdfile.readFile("paths.txt");
	@Test
	public void pathUtils_returns_true_if__Seoul_is_present_in_database(){
 		PathUtils paths = new PathUtils(content);
 		assertTrue(paths.isCityPresent("Seoul"));
	}

	@Test
	public void pathUtils_returns_true_if__dubai_is_present_in_database(){
 		PathUtils paths = new PathUtils(content);
 		assertTrue(paths.isCityPresent("Dubai"));
	}

	@Test
	public void pathUtils_returns_false_if__India_is_not_in_database(){
 		PathUtils paths = new PathUtils(content);
 		assertFalse(paths.isCityPresent("India"));
	}

	@Test
	public void isDirectpath_returns_true_if_direct_flight_between_Bejing_to_Tokyo(){
 		PathUtils paths = new PathUtils(content);
 		assertTrue(paths.isDirectPath("Beijing", "Tokyo"));
	}

	@Test
	public void isDirectpath_returns_true_if_direct_flight_between_Singapore_to_Dubai(){
 		PathUtils paths = new PathUtils(content);
 		assertTrue(paths.isDirectPath("Singapore","Dubai"));
	}

	@Test
	public void Directpath_returns_false_if_no_direct_flight_between_Singapore_to_Chennai(){
 		PathUtils paths = new PathUtils(content);
 		assertFalse(paths.isDirectPath("Singapore","Chennai"));
	}

	@Test
	public void Directpath_returns_true_if_flight_between_bangalore_to_tokyo(){
 		PathUtils paths = new PathUtils(content);
 		assertTrue(paths.isDirectPath("Bangalore","Tokyo"));
	}

	@Test
	public void Directpath_returns_false_if_no_flight_between_bangalore_to_chennai(){
 		PathUtils paths = new PathUtils(content);
 		assertFalse(paths.isDirectPath("Bangalore" , "Chennai"));
	}

	@Test
	public void getFullPath_returns_the_path_between_bangalore_to_Seoul(){
 		PathUtils paths = new PathUtils(content);
 		assertEquals(paths.getFullPath("Bangalore" , "Seoul"),"Bangalore-->Singapore-->Seoul");
	}

	@Test
	public void getFullPath_gives_the_full_path_between_bangalore_to_Tokyo(){
 		PathUtils paths = new PathUtils(content);
 		assertEquals(paths.getFullPath("Bangalore" , "Tokyo"),"Bangalore-->Singapore-->Seoul-->Beijing-->Tokyo");
	}

	
}