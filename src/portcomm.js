<script src="http://192.168.0.200:8097"></script>
import React, { Component } from 'react';
import { StyleSheet, Text, View, TouchableOpacity ,ToastAndroid} from 'react-native';
import { NativeModules,DeviceEventEmitter } from 'react-native'
import SerialPortAPI from 'react-native-serial-port-api';

class PortComm extends Component {
    sub;
    serialPort;
    testinterval;
    constructor(props) {
        super(props);


    }
    async componentDidMount() {
       
        NativeModules.MyHeadsetLibModule.startTrackingAudioJackPlug()
        DeviceEventEmitter.addListener('Got_Cha', (e)=> {

            ToastAndroid.showWithGravity(
                "Data Test Found    "+e,
                ToastAndroid.SHORT,
                ToastAndroid.CENTER
              );
           
        
        })
        DeviceEventEmitter.addListener('BroadCast_Received', (e)=> {

            ToastAndroid.showWithGravity(
                "temp found    "+e,
                ToastAndroid.SHORT,
                ToastAndroid.CENTER
              );
            
         
         })


    }
      example() {
        // const serialPort = await SerialPortAPI.open("/dev/ttyS3", { baudRate: 38400 });

        // // subscribe received data
        // const sub = serialPort.onReceived(buff => {


        //     console.log(buff.toString('hex').toUpperCase());
        // })

        // // unsubscribe
        // // sub.remove();

        // // send data with hex format
        // await serialPort.send('00FF');

        // // close
        // serlialPort.close();
        this.testinterval=  setInterval(()=>{
            NativeModules.MyHeadsetLibModule.TestIntent();
            
        },1500)
       


    }
     getBodyTemp(){
         clearInterval(this.testinterval);
        setInterval(()=>{
            NativeModules.MyHeadsetLibModule.someMethod();
            
        },1500)
    }
    componentWillUnmount() {
        this.sub ? this.sub.remove() : null;
    }

    render() {
        return (
            <View>
                <Text>Open up {JSON.stringify(SerialPortAPI)}</Text>
                <TouchableOpacity
                    style={styles.button}
                    onPress={() => this.getBodyTemp()}
                >
                    <Text style={styles.buttonText}>BodyTemp</Text>
                </TouchableOpacity>
                <TouchableOpacity
                    style={styles.button}
                    onPress={() => this.example()}
                >
                    <Text style={styles.buttonText}>Test Working</Text>
                </TouchableOpacity>
            </View>
        )
    }

}
const styles = StyleSheet.create({
    full: {
        flex: 1
    },
    body: {
        flex: 1
    },
    container: {
        flex: 1,
        marginTop: 20,
        marginLeft: 16,
        marginRight: 16
    },
    header: {
        display: "flex",
        justifyContent: "center"
        //alignItems: "center"
    },
    line: {
        display: "flex",
        flexDirection: "row"
    },
    line2: {
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-between"
    },
    title: {
        width: 100
    },
    value: {
        marginLeft: 20
    },
    output: {
        marginTop: 10,
        height: 300,
        padding: 10,
        backgroundColor: "#FFFFFF",
        borderWidth: 1
    },
    inputContainer: {
        marginTop: 10,
        borderBottomWidth: 2
    },
    textInput: {
        paddingLeft: 10,
        paddingRight: 10,
        height: 40
    },
    button: {
        marginTop: 16,
        marginBottom: 16,
        paddingLeft: 15,
        paddingRight: 15,
        height: 40,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#147efb",
        borderRadius: 3
    },
    buttonText: {
        color: "#FFFFFF"
    }
});

export default PortComm;
