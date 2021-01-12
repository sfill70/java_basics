import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private String name;
    private String birthDay;
//    private model.Voter voter;
//    private model.WorkTime workTime;
//    private HashMap<model.Voter, Byte> voterCounts;
//    private HashMap<Short, model.WorkTime> voteStationWorkTimes;
//    private static byte val = 1;

    public XMLHandler() {
        /*this.voterCounts = new HashMap<>();
        this.voteStationWorkTimes = new HashMap<>();*/
    }

//    public HashMap<model.Voter, Byte> getVoterCounts() {
//        return voterCounts;
//    }

//    public HashMap<Short, model.WorkTime> getVoteStationWorkTimes() {
//        return voteStationWorkTimes;
//    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                name = attributes.getValue("name");
                birthDay = attributes.getValue("birthDay");
                /*voter = new model.Voter(attributes.getValue("name"),
                        attributes.getValue("birthDay"));*/

            } else if (qName.equals("visit") && name != null && birthDay != null) {
                /*voterCounts.merge(voter, val, (oldVal, newVal) -> (byte) (oldVal + newVal));*/
                DBConnection.countVoter(name, birthDay);
                /*String stStation = attributes.getValue("station");
                String stTime = attributes.getValue("time");
                fillingVoteStationWorkTimes(stStation, stTime);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* private void fillingVoteStationWorkTimes(String stStation, String stTime) {
        Short station = Short.parseShort(stStation);
        Date time = null;
        try {
            time = visitDateFormat.parse(stTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        workTime = voteStationWorkTimes.get(station);
        if (workTime == null) {
            workTime = new model.WorkTime();
            voteStationWorkTimes.put(station, workTime);
        }
        workTime.addVisitTime(time.getTime());
    }*/

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
           /* voter = null;*/
            name = null;
            birthDay = null;
        }
        /*if (qName.equals("visit")) {
            workTime = null;
        }*/
    }

    /*public void printVoteStationWorkTimes() {
        System.out.println("Voting station work times: ");
        for (Short votingStation : voteStationWorkTimes.keySet()) {
            model.WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }*/

    /*public void printVoterCounts() {
        System.out.println("Duplicated voters: ");
        for (model.Voter voter : voterCounts.keySet()) {
            short count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + "-" + count);
            }
        }
    }*/
}