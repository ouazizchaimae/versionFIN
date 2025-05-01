import {SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {globalStyle} from "../../../../../../shared/globalStyle";
import {truncateText} from "../../../../../../shared/utils";


const RatioUniteAdminCard = ({ entiteName ,produitName ,ratio , onPressDelete, onUpdate, onDetails }) =>{

return (

    <SafeAreaView>
        <TouchableOpacity onPress={onDetails} style={globalStyle.card}>
            <ScrollView horizontal>
                <View style={globalStyle.contentContainer}>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Entite: </Text>
                        <Text style={globalStyle.value}>{truncateText(entiteName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Produit: </Text>
                        <Text style={globalStyle.value}>{truncateText(produitName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Ratio: </Text>
                        <Text style={globalStyle.value}>{truncateText(ratio)}</Text>
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

export default RatioUniteAdminCard;
