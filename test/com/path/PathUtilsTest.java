package com.path;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PathUtilsTest {
    ReadFile rdfile = new ReadFile();
    String pathFile = rdfile.readFile("test/com/path/paths.txt");
    String cityFile = rdfile.readFile("cities.txt");
    Queue<String> path = new LinkedList<String>();
    List<Queue<String>> expected = new ArrayList<Queue<String>>();
    PathUtils paths = new PathUtils(pathFile, cityFile);
    List<String> result = new ArrayList<String>();
    List<Integer>result1 = new ArrayList<Integer>();


    @Test
    public void pathUtils_returns_true_if__Seoul_is_present_in_database() {
        assertTrue(paths.isCityPresent("Seoul"));
    }

    @Test
    public void pathUtils_returns_true_if__dubai_is_present_in_database() {
        assertTrue(paths.isCityPresent("Dubai"));
    }

    @Test
    public void pathUtils_returns_false_if__India_is_not_in_database() {
        assertFalse(paths.isCityPresent("India"));
    }

    @Test
    public void getDirectpath_returns__direct_path_flight_between_Bejing_to_Tokyo() {
         path.add("Beijing");
         path.add("Tokyo");
        expected.add(new LinkedList<String>(path));
        assertEquals(paths.getDirectPath("Beijing", "Tokyo"), expected);
    }

    @Test
    public void getDirectpath_returns_direct_path_between_Tokyo_to_Beijing() {
        path.add("Tokyo");
        path.add("Beijing");
        expected.add(new LinkedList<String>(path));
        assertEquals(paths.getDirectPath("Tokyo", "Beijing"), expected);
    }
//
    @Test
    public void getDirectpath__gives_path_direct_between_Singapore_to_Dubai() {
        path.add("Singapore");
        path.add("Dubai");
        expected.add(new LinkedList<String>(path));
        assertEquals(paths.getDirectPath("Singapore", "Dubai"), expected);
    }
    @Test
    public void getFullPath_returns_the_path_between_bangalore_to_Seoul() {

        result.add("Bangalore[India]-->Singapore[Singapore]-->Seoul[South Korea]");
        result.add("Bangalore[India]-->Singapore[Singapore]-->Dubai[UAE]-->Seoul[South Korea]");

        assertEquals(paths.getFullPath("Bangalore","Seoul"),result);
    }
    @Test
    public void getFullPath_gives_the_full_path_between_bangalore_to_Tokyo() {
        result.add("Bangalore[India]-->Singapore[Singapore]-->Seoul[South Korea]-->Beijing[China]-->Tokyo[Japan]");
        result.add("Bangalore[India]-->Singapore[Singapore]-->Dubai[UAE]-->Seoul[South Korea]-->Beijing[China]-->Tokyo[Japan]");
        assertEquals(paths.getFullPath("Bangalore", "Tokyo"), result);
    }
    @Test
    public void getFullPath_gives_the_full_path_between_Tokyo_to_Bangalore() {
        result.add("Tokyo[Japan]-->Beijing[China]-->Seoul[South Korea]-->Singapore[Singapore]-->Bangalore[India]");
        assertEquals(paths.getFullPath("Tokyo", "Bangalore"),result );
    }
    @Test
    public void TotalCost_gives_the_fullCost_between_Bangalore_To_Singapore() {
         result1.add(7000);
        assertEquals(paths.TotalCost("Bangalore", "Singapore"), result1);

    }
    @Test
    public void TotalCost_gives_the_fullCost_between_Bangalore_To_Dubai() {
        result1.add(12500);
        assertEquals(paths.TotalCost("Bangalore", "Dubai"),result1 );

    }
    @Test
    public void TotalCost_gives_the_fullCost_between_Bangalore_To_Tokyo() {
        result1.add(23000);
        result1.add(31500);
        assertEquals(paths.TotalCost("Bangalore", "Tokyo"),result1 );

    }
}