package com.topcoder.utilities;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.topcoder.shared.util.StringUtil;


/**
 * Utility class for parsing log file generated by the Contest processor.
 *
 * @author Diego Belfer (mural)
 * @version $Id: ProcessorLogParser.java 56700 2007-01-29 21:13:11Z thefaxman $
 */
public class ProcessorLogParser {
    private static String COMPILE = "(.{23,23})(.*)processing a compile request, userID=([0-9]+), language=([0-9]+) component=([0-9]+)";
    private static String TEST = "(.{23,23})(.*) ([0-9]+) is tesing component ([0-9]+) with args: (.*)$";
    private static String CHALLENGE = "(.{23,23})(.*) ([0-9]+) is attempting to challenge ([0-9]*) on component ([0-9]+) with (.*)$";
    private static String REGISTER = "(.{23,23})(.*) Register: id=([0-9]+), handle=(.*), lang=(.*)$";
    private static String OPEN = "(.{23,23})(.*) OPEN coder:([0-9]+) (.*) component: ([0-9]+)";
    private static String ENTER = "(.{23,23})(.*) ([0-9]+) entering round .*";
    private static String MOVE = "(.{23,23})(.*) Moving ([0-9]+) to ([0-9]+)";
    private static String SUBMIT = "(.{23,23})(.*) (.*) is attempting to submit ([0-9]+)";
    private static String PHASE  = "(.{23,23})(.*) \\(PhaseChange\\)\\[([0-9]+).*";

    private Map phases = new LinkedHashMap();
    private Map infoByUser = new HashMap();

    private String fileName;
    private Pattern compilePattern;
    private Pattern testPattern;
    private Pattern challengePattern;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss,SSS");
    private DateFormat dateFormatDisplay = new SimpleDateFormat("kk:mm:ss,SSS");
    private Pattern openPattern;
    private Pattern enterPattern;
    private Pattern submitPattern;
    private Pattern registerPattern;
    private Map users = new HashMap();
    private Pattern movePattern;
    private Pattern phasePattern;


    public ProcessorLogParser(String fileName) {
        this.fileName = fileName;
        this.compilePattern = Pattern.compile(COMPILE);
        this.testPattern = Pattern.compile(TEST);
        this.challengePattern = Pattern.compile(CHALLENGE);
        this.openPattern  = Pattern.compile(OPEN);
        this.enterPattern  = Pattern.compile(ENTER);
        this.submitPattern  = Pattern.compile(SUBMIT);
        this.registerPattern  = Pattern.compile(REGISTER);
        this.movePattern  = Pattern.compile(MOVE);
        this.phasePattern  = Pattern.compile(PHASE);
        //this.users = loadUsers();

    }


    public void parse() throws Exception {
        BufferedReader fileContents = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(new File(fileName)))));
        while (fileContents.ready()) {
            String line = fileContents.readLine();
            if (!tryCompile(line) &&
                    !tryUserTest(line) &&
                    !tryChallenge(line) &&
                    !tryOpen(line) &&
                    !tryEnter(line) &&
                    !trySubmit(line) &&
                    !tryRegister(line) &&
                    !tryMove(line) &&
                    !tryPhase(line)) {
                //System.out.println(line);
            }
        }
        writeOutput(new PrintStream(new FileOutputStream("out.log")));
    }


    private void writeOutput(PrintStream printStream) {
        printStream.println("Users: "+infoByUser.size());
        printStream.println("Unregistered submits: "+ (infoByUser.containsKey("-1") ? ((ConnectionInfo)infoByUser.get("-1")).events.size(): 0));
        Iterator it = infoByUser.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String coderId = StringUtil.padLeft((String) entry.getKey(),10);
            //printStream.println("coderId="+coderId+" --------------------------------------------------------");
            ConnectionInfo value = (ConnectionInfo) entry.getValue();
            List events = value.events;
            //Iterator<Entry<String, Date>> phaseIt = phases.entrySet().iterator();
            //Entry<String, Date> nextPhase = nextPhase(printStream, phaseIt, phaseIt.next());
            for (Iterator it1 = events.iterator(); it1.hasNext(); ) {
                EventInfo event = (EventInfo) it1.next();
//                if (nextPhase != null && event.time.after(nextPhase.getValue())) {
//                    nextPhase = nextPhase(printStream, phaseIt, nextPhase);
//                }
                printStream.println(coderId+ " " + event);
            }
        }
    }


    private Entry nextPhase(PrintStream printStream, Iterator phaseIt, Entry phase) {
        printStream.println("   "+dateFormatDisplay.format(phase.getValue())+" Starts phase " + phase.getKey());
        if (phaseIt.hasNext()) {
            return (Entry) phaseIt.next();
        }
        return null;
    }

    private boolean tryCompile(String line) throws ParseException {
        Matcher matcher = compilePattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String languageId = matcher.group(4);
            String componentId = matcher.group(5);
            addEventForUser(userId, EventInfo.compile(componentId, dateFormat.parse(time), languageId, line));
            return true;
        }
        return false;
    }

    private boolean tryUserTest(String line) throws ParseException {
        Matcher matcher = testPattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String componentId = matcher.group(4);
            String args = matcher.group(5);
            addEventForUser(userId, EventInfo.userTest(componentId, dateFormat.parse(time), args, line));
            return true;
        }
        return false;
    }

    private boolean tryChallenge(String line) throws ParseException {
        Matcher matcher = challengePattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String defendantId = matcher.group(4);
            String componentId = matcher.group(5);
            addEventForUser(userId, EventInfo.challenge(componentId, dateFormat.parse(time), defendantId, line));
            return true;
        }
        return false;
    }

    private boolean tryOpen(String line) throws ParseException {
        Matcher matcher = openPattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String componentId = matcher.group(5);
            addEventForUser(userId, EventInfo.openComponent(componentId, dateFormat.parse(time), line));
            return true;
        }
        return false;
    }

    private boolean tryEnter(String line) throws ParseException {
        Matcher matcher = enterPattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            addEventForUser(userId, EventInfo.enter(dateFormat.parse(time), line));
            return true;
        }
        return false;
    }

    private boolean tryMove(String line) throws ParseException {
        Matcher matcher = movePattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String roomId = matcher.group(4);
            addEventForUser(userId, EventInfo.move(dateFormat.parse(time), line, roomId));
            return true;
        }
        return false;
    }

    private boolean trySubmit(String line) throws ParseException {
        Matcher matcher = submitPattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = resolveUserId(matcher.group(3));

            addEventForUser(userId, EventInfo.submit(dateFormat.parse(time), line));
            return true;
        }
        return false;
    }

    private boolean tryRegister(String line) throws ParseException {
        Matcher matcher = registerPattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String userId = matcher.group(3);
            String handle= matcher.group(4);
            users.put(handle, userId);
            addEventForUser(userId, EventInfo.register(dateFormat.parse(time), line));
            return true;
        }
        return false;
    }


    private boolean tryPhase(String line) throws ParseException {
        Matcher matcher = phasePattern.matcher(line);
        if (matcher.matches()) {
            String time = matcher.group(1);
            String phase = matcher.group(3);
            if (!phases.containsKey(phase)) {
                phases.put(phase, dateFormat.parse(time));
            }
            return true;
        }
        return false;
    }

    private String resolveUserId(String handle) {
        String userId = (String) users.get(handle);
        if (userId == null) {
            return "-1";
        }
        return userId;
    }

    private void addEventForUser(String userId, EventInfo event) {
        ConnectionInfo info = (ConnectionInfo) infoByUser.get(userId);
        if  (info == null) {
            info = new ConnectionInfo(userId);
            infoByUser.put(userId, info);
        }
        info.addEvent(event);
    }

    private Properties loadUsers() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("users.properties"));
        } catch (IOException e) {
            try {
                props.load(new FileInputStream("users.properties"));
            } catch (IOException e1) {
                throw new RuntimeException("users.properties must be in the classpath");
            }
        }
        return props;
    }

    public static void main(String[] args) throws Exception {
        new ProcessorLogParser(args[0]).parse();
    }

    static class ConnectionInfo {
        private String userId;
        private List events = new LinkedList();

        public ConnectionInfo(String userId) {
            this.userId = userId;
        };

        public void addEvent(EventInfo event) {
            events.add(event);
        }
    }

    static class EventInfo {
        private DateFormat dateFormat = new SimpleDateFormat("kk:mm:ss SSS");
        public static final int COMPILE = 1;
        public static final int USER_TEST = 2;
        public static final int CHALLENGE_TEST = 3;
        public static final int OPEN_COMPONENT = 4;
        public static final int ENTER = 5;
        public static final int SUBMIT = 6;
        public static final int REGISTER = 7;
        public static final int MOVE = 8;

        int type;
        String componentId;
        Date time;
        String extra;
        String line;

        public EventInfo(int type, String componentId, Date time, String line, String extra) {
            this.type = type;
            this.componentId = componentId;
            this.time = time;
            this.line = line;
            this.extra = extra;
        }


        public static EventInfo compile(String componentId, Date time, String languageId, String line) {
            return new EventInfo(COMPILE, componentId, time, line, languageId);
        }
        public static EventInfo userTest(String componentId, Date time, String args, String line) {
            return new EventInfo(USER_TEST, componentId, time, line, args);
        }
        public static EventInfo challenge(String componentId, Date time, String defendantId, String line) {
            return new EventInfo(CHALLENGE_TEST, componentId, time, line, defendantId);
        }
        public static EventInfo openComponent(String componentId, Date time, String line) {
            return new EventInfo(OPEN_COMPONENT, componentId, time, line, null);
        }
        public static EventInfo enter(Date time, String line) {
            return new EventInfo(ENTER, null, time, line, null);
        }
        public static EventInfo submit(Date time, String line) {
            return new EventInfo(SUBMIT, null, time, line, null);
        }
        public static EventInfo register(Date time, String line) {
            return new EventInfo(REGISTER, null, time, line, null);
        }
        public static EventInfo move(Date time, String line, String roomId) {
            return new EventInfo(MOVE, null, time, line, roomId);
        }
        public String toString() {
            return dateFormat.format(time)+" "+getEventName(type)+" "+StringUtil.padLeft(""+componentId,10)+" "+extra;
        }

        private String getEventName(int type2) {
            switch (type2) {
                case COMPILE:        return "COMPILE  ";
                case USER_TEST:      return "USRTEST  ";
                case CHALLENGE_TEST: return "CHALLENGE";
                case OPEN_COMPONENT: return "OPEN     ";
                case ENTER:          return "ENTER    ";
                case MOVE:           return "MOVE     ";
                case SUBMIT:         return "SUBMIT   ";
                case REGISTER:       return "REGISTER ";
                default:             return "!!ERROR!!";
            }
        }
    }
}