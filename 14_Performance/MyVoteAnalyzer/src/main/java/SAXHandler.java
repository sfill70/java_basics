import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SAXHandler extends DefaultHandler {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private Voter voter;
    private WorkTime workTime;
    private HashMap<Voter, Byte> voterCounts;
    private HashMap<Short, WorkTime> voteStationWorkTimes;

    public SAXHandler() {
        this.voterCounts = new HashMap<>();
        this.voteStationWorkTimes = new HashMap<>();
    }

    public HashMap<Voter, Byte> getVoterCounts() {
        return voterCounts;
    }

    public HashMap<Short, WorkTime> getVoteStationWorkTimes() {
        return voteStationWorkTimes;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                voter = new Voter(attributes.getValue("name"),
                        attributes.getValue("birthDay"));
            } else if (qName.equals("visit") && voter != null) {
                voterCounts.merge(voter, (byte)1, (oldVal, newVal) -> (byte)(oldVal + newVal));
                String stStation = attributes.getValue("station");
                String stTime = attributes.getValue("time");
                fillingVoteStationWorkTimes(stStation, stTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillingVoteStationWorkTimes(String stStation, String stTime) {
        Short station = Short.parseShort(stStation);
        Date time = null;
        try {
            time = visitDateFormat.parse(stTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        workTime = voteStationWorkTimes.get(station);
        if (workTime == null) {
            workTime = new WorkTime();
            voteStationWorkTimes.put(station, workTime);
        }
        workTime.addVisitTime(time.getTime());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
        if (qName.equals("visit")) {
            workTime = null;
        }
    }

    public void printVoteStationWorkTimes() {
        System.out.println("Voting station work times: ");
        for (Short votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
    }

    public void printVoterCounts() {
        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println(voter.toString() + "-" + count);
            }
        }
    }
}