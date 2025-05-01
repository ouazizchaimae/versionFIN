import {SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {globalStyle} from "../../../../../../shared/globalStyle";
import {truncateText} from "../../../../../../shared/utils";


const SuiviProductionAdminCard = ({ code ,libelle ,description ,jour ,volume ,tsm ,produitName ,stadeOperatoireName ,uniteName , onPressDelete, onUpdate, onDetails }) =>{

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
                        <Text style={globalStyle.label}>Jour: </Text>
                        <Text style={globalStyle.value}>{truncateText(jour)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Volume: </Text>
                        <Text style={globalStyle.value}>{truncateText(volume)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Tsm: </Text>
                        <Text style={globalStyle.value}>{truncateText(tsm)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Produit: </Text>
                        <Text style={globalStyle.value}>{truncateText(produitName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Stade operatoire: </Text>
                        <Text style={globalStyle.value}>{truncateText(stadeOperatoireName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Unite: </Text>
                        <Text style={globalStyle.value}>{truncateText(uniteName)}</Text>
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

export default SuiviProductionAdminCard;
