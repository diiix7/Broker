import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
// import * as BrokerService from "./modules/rightqnative/index";

export default function App() {
  // useEffect(() => {
  //   console.log("Broker starting...: ", BrokerService.startBroker());
  // }, []);

  return (
    <View style={styles.container}>
      <Text>RightQ Broker</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
