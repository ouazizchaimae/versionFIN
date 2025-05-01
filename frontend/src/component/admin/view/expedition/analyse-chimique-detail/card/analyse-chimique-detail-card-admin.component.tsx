import {SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {globalStyle} from "../../../../../../shared/globalStyle";
import {truncateText} from "../../../../../../shared/utils";


const AnalyseChimiqueDetailAdminCard = ({ libelle ,description ,elementChimiqueName ,valeur ,conformite ,surqualite ,analyseChimiqueName , onPressDelete, onUpdate, onDetails }) =>{

return (

    <SafeAreaView>
        <TouchableOpacity onPress={onDetails} style={globalStyle.card}>
            <ScrollView horizontal>
                <View style={globalStyle.contentContainer}>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Libelle: </Text>
                        <Text style={globalStyle.value}>{truncateText(libelle)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Description: </Text>
                        <Text style={globalStyle.value}>{truncateText(description)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Element chimique: </Text>
                        <Text style={globalStyle.value}>{truncateText(elementChimiqueName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Valeur: </Text>
                        <Text style={globalStyle.value}>{truncateText(valeur)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Conformite: </Text>
                        <Text style={globalStyle.value}>{truncateText(conformite)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Surqualite: </Text>
                        <Text style={globalStyle.value}>{truncateText(surqualite)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Analyse chimique: </Text>
                        <Text style={globalStyle.value}>{truncateText(analyseChimiqueName)}</Text>
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

export default AnalyseChimiqueDetailAdminCard;
