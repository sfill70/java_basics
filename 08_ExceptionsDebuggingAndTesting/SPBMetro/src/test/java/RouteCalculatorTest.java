import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    List<Station> routeDuration;
    StationIndex stationIndexTest = new StationIndex();
    RouteCalculator routeCalculator;

    Line line1, line2, line3;

    Station stationA, stationB, stationC, stationD, stationE, stationF, stationG, stationH, stationI, stationJ;

   /*interStationDuration = 2.5;
    interConnectionDuration = 3.5;*/

    public RouteCalculatorTest() {
        super();
    }

    /* Схема тестового метро.
     *  A----B-----C-----D
     *       E     H
     *       |     |
     *       F     I
     *       |     |
     *       G     J
     */

    //но это не точно возможно @BeforeClass
    @Before
    public void setUp() throws Exception {
        route = new ArrayList<>();
        routeDuration = new ArrayList<>();

        line1 = new Line(1, "one");
        line2 = new Line(2, "tow");
        line3 = new Line(3, "three");

        routeAddAll(stationA = new Station("A", line1));
        routeAddAll(stationB = new Station("B", line1));
        routeAddAll(stationC = new Station("C", line1));
        route.add(stationD = new Station("D", line1));
        route.add(stationE = new Station("E", line2));
        route.add(stationF = new Station("F", line2));
        route.add(stationG = new Station("G", line2));
        routeAddAll(stationH = new Station("H", line3));
        routeAddAll(stationI = new Station("I", line3));
        routeAddAll(stationJ = new Station("J", line3));

        stationIndexTest.addLine(line1);
        stationIndexTest.addLine(line2);
        stationIndexTest.addLine(line3);
//        System.out.println(stationIndexTest.number2line);
        stationIndexTest.stations.addAll(line1.getStations());
        stationIndexTest.stations.addAll(line2.getStations());
        stationIndexTest.stations.addAll(line3.getStations());
//        System.out.println(stationIndexTest.stations);
        stationIndexTest.addConnection(Arrays.asList(stationB, stationE));
        stationIndexTest.addConnection(Arrays.asList(stationC, stationH));
//        System.out.println(stationIndexTest.connections);
        routeCalculator = new RouteCalculator(stationIndexTest);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private void routeAddAll(Station station) {
        route.add(station);
        routeDuration.add(station);
    }

    @Test
    public void testGetShortestRoute() {
//       проверка getRouteOnTheLine(from, to)
        List<Station> listActual = routeCalculator.getShortestRoute(stationA, stationD);
        List<Station> listExpected = Arrays.asList(stationA, stationB, stationC, stationD);
        Assert.assertEquals(listExpected, listActual);
//      проверка  getRouteWithOneConnection(from, to);
        listActual = routeCalculator.getShortestRoute(stationA, stationJ);
        listExpected = Arrays.asList(stationA, stationB, stationC, stationH, stationI, stationJ);
        Assert.assertEquals(listExpected, listActual);
//      проверка  getRouteWithTwoConnections(from, to);
        listActual = routeCalculator.getShortestRoute(stationG, stationJ);
        listExpected = Arrays.asList(stationG, stationF, stationE, stationB, stationC, stationH, stationI, stationJ);
        Assert.assertEquals(listExpected, listActual);
    }

    @Test
    public void testGetShortestRouteNoNull(){
        List<Station> listExpected = routeCalculator.getShortestRoute(stationF, stationG);
        Assert.assertNotNull(listExpected);
    }


    @Test
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(routeDuration);
        double expected = 13.5;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testCalculateDurationNoNull() {
        double expected = RouteCalculator.calculateDuration(route);
        Assert.assertNotNull(expected);
    }

//    Остальные методы private

    @Test
    public void testGetRouteOnTheLine() {
        List<Station> listActual = routeCalculator.getShortestRoute(stationA, stationD);
        List<Station> listExpected = Arrays.asList(stationA, stationB, stationC, stationD);
        Assert.assertEquals(listExpected, listActual);

    }

    @Test
    public void testGetRouteWithOneConnection() {
        List<Station> listActual = routeCalculator.getShortestRoute(stationA, stationJ);
        List<Station> listExpected = Arrays.asList(stationA, stationB, stationC, stationH, stationI, stationJ);
        Assert.assertEquals(listExpected, listActual);
    }

    @Test
    public void testIsConnected() {
    }

    @Test
    public void testGetRouteViaConnectedLine() {
    }

    @Test
    public void testGetRouteWithTwoConnections() {
        List<Station> listActual = routeCalculator.getShortestRoute(stationG, stationJ);
        List<Station> listExpected = Arrays.asList(stationG, stationF, stationE, stationB, stationC, stationH, stationI, stationJ);
        Assert.assertEquals(listExpected, listActual);
    }
}