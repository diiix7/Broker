import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View } from "react-native";
import React, { useEffect, useState } from "react";
import * as Updates from "expo-updates";

export default function App() {
  async function onFetchUpdateAsync() {
    try {
      const update = await Updates.checkForUpdateAsync();
      if (update.isAvailable) {
        await Updates.fetchUpdateAsync();
        await Updates.reloadAsync();
      }
      //alert("The app has been updated !");
    } catch (error) {
      // alert("Error updating the app !");
      console.log(`Error updating the app ! ${error}`);
    }
  }

  useEffect(() => {
    onFetchUpdateAsync();
  }, []);

  return (
    <View style={{ flex: 1, justifyContent: "center", alignSelf: "center" }}>
      <Text style={{ textAlign: "center", fontSize: 20 }}>RightQ Broker</Text>
      <View style={{ textAlign: "center", top: "40%" }}>
        <Text style={{ textAlign: "center", fontSize: 20 }}>- - - - - -</Text>
        <Text>Powered by RightQ</Text>
      </View>
    </View>
  );
}
