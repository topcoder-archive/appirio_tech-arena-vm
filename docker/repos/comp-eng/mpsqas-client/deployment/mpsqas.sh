CLASSPATH=.
for i in `ls -1 ./*.jar`; do
     CLASSPATH=$CLASSPATH:$i
done;

IP=@applet.host@
java -cp $CLASSPATH com.topcoder.client.mpsqasApplet.LaunchMPSQAS $IP @applet.port@
