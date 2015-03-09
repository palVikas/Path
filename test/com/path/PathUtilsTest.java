package com.path;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PathUtilsTest {
    ReadFile rdfile = new ReadFile();
    String pathFile = rdfile.readFile("paths.txt");
    String cityFile = rdfile.readFile("cities.txt");

    @Test
    public void pathUtils_returns_true_if__Seoul_is_present_in_database() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isCityPresent("Seoul"));
    }

    @Test
    public void pathUtils_returns_true_if__dubai_is_present_in_database() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isCityPresent("Dubai"));
    }

    @Test
    public void pathUtils_returns_false_if__India_is_not_in_database() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertFalse(paths.isCityPresent("India"));
    }

    @Test
    public void isDirectpath_returns_true_if_direct_flight_between_Bejing_to_Tokyo() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isDirectPath("Beijing", "Tokyo"));
    }

    @Test
    public void isDirectpath_returns_true_if_direct_flight_between_Tokyo_to_Beijing() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isDirectPath("Tokyo", "Beijing"));
    }

    @Test
    public void isDirectpath_returns_true_if_direct_flight_between_Singapore_to_Dubai() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isDirectPath("Singapore", "Dubai"));
    }

    @Test
    public void Directpath_returns_false_if_no_direct_flight_between_Singapore_to_Chennai() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertFalse(paths.isDirectPath("Singapore", "Chennai"));
    }

    @Test
    public void Directpath_returns_true_if_flight_between_bangalore_to_tokyo() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertTrue(paths.isDirectPath("Bangalore", "Tokyo"));
    }

    @Test
    public void Directpath_returns_false_if_no_flight_between_bangalore_to_chennai() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertFalse(paths.isDirectPath("Bangalore", "Chennai"));
    }

    @Test
    public void getFullPath_returns_the_path_between_bangalore_to_Seoul() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertEquals(paths.getFullPath("Bangalore", "Seoul"), "Bangalore[India]-->Singapore[Singapore]-->Seoul[South Korea]");
    }

    @Test
    public void getFullPath_gives_the_full_path_between_bangalore_to_Tokyo() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertEquals(paths.getFullPath("Bangalore", "Tokyo"), "Bangalore[India]-->Singapore[Singapore]-->Seoul[South Korea]-->Beijing[China]-->Tokyo[Japan]");
    }

    @Test
    public void getFullPath_gives_the_full_path_between_Tokyo_to_Bangalore() {
        PathUtils paths = new PathUtils(pathFile, cityFile);
        assertEquals(paths.getFullPath("Tokyo", "Bangalore"), "Tokyo[Japan]-->Beijing[China]-->Seoul[South Korea]-->Singapore[Singapore]-->Bangalore[India]");
    }
}