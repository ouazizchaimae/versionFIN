import {SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {globalStyle} from "../../../../../../shared/globalStyle";
import {truncateText} from "../../../../../../shared/utils";


const ExpeditionAdminCard = ({ code ,libelle ,description ,clientName ,typeExpeditionName , onPressDelete, onUpdate, onDetails }) =>{

return (

    <SafeAreaView>
        <TouchableOpacity onPress={onDetails} style={globalStyle.card}>
            <ScrollView horizontal>
                <View style={globalStyle.contentContainer}>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Code: </Text>
                        <Text style={globalStyle.value}>{truncateText(code)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Libelle: </Text>
                        <Text style={globalStyle.value}>{truncateText(libelle)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Description: </Text>
                        <Text style={globalStyle.value}>{truncateText(description)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Client: </Text>
                        <Text style={globalStyle.value}>{truncateText(clientName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Type expedition: </Text>
                        <Text style={globalStyle.value}>{truncateText(typeExpeditionName)}</Text>
                    </View>

                </View>
    </ScrollView>
    <View style={globalStyle.buttonsContainer}>
        <TouchableOpacity onPress={onPressDelete} style={globalStyle.button}>
            <Ionicons name="trash" size={25} color={'red'} />
        </TouchableOpacity>
        <TouchableOpacity onPress={onUpdate} style={globalStyle.button}>
            <Ionicons name="create" size={25} color={'green'} />
        </TouchableOpacity>
    </View>
</TouchableOpacity>
</SafeAreaView>
);
};

export default ExpeditionAdminCard;
