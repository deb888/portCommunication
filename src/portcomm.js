<script src="http://192.168.0.200:8097"></script>
import React, { Component } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import { NativeModules,DeviceEventEmitter } from 'react-native'
import SerialPortAPI from 'react-native-serial-port-api';

class PortComm extends Component {
    sub;
    serialPort;
    constructor(props) {
        super(props);


    }
    async componentDidMount() {
       
        NativeModules.MyHeadsetLibModule.startTrackingAudioJackPlug()
        DeviceEventEmitter.addListener('Got_Cha', (e)=> {

           console.log(e);
           
        
        })
        DeviceEventEmitter.addListener('BroadCast_Received', (e)=> {

            console.log(e);
            
         
         })


    }
    async  example() {
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
        NativeModules.MyHeadsetLibModule.someMethod()


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
                    onPress={() => this.example()}
                >
                    <Text style={styles.buttonText}>Send</Text>
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
