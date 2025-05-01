import {SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React from 'react';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {globalStyle} from "../../../../../../shared/globalStyle";
import {truncateText} from "../../../../../../shared/utils";


const DemandeAdminCard = ({ code ,libelle ,description ,produitMarchandName ,clientName ,dateDemande ,dateExpedition ,volume ,typeDemandeName ,etatDemandeName ,actionEntreprise ,trg ,cause ,commentaire , onPressDelete, onUpdate, onDetails }) =>{

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
                        <Text style={globalStyle.label}>Produit marchand: </Text>
                        <Text style={globalStyle.value}>{truncateText(produitMarchandName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Client: </Text>
                        <Text style={globalStyle.value}>{truncateText(clientName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Date demande: </Text>
                        <Text style={globalStyle.value}>{truncateText(dateDemande)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Date expedition: </Text>
                        <Text style={globalStyle.value}>{truncateText(dateExpedition)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Volume: </Text>
                        <Text style={globalStyle.value}>{truncateText(volume)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Type demande: </Text>
                        <Text style={globalStyle.value}>{truncateText(typeDemandeName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Etat demande: </Text>
                        <Text style={globalStyle.value}>{truncateText(etatDemandeName)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Action entreprise: </Text>
                        <Text style={globalStyle.value}>{truncateText(actionEntreprise)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Trg: </Text>
                        <Text style={globalStyle.value}>{truncateText(trg)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Cause: </Text>
                        <Text style={globalStyle.value}>{truncateText(cause)}</Text>
                    </View>
                    <View style={globalStyle.infos}>
                        <Text style={globalStyle.label}>Commentaire: </Text>
                        <Text style={globalStyle.value}>{truncateText(commentaire)}</Text>
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

export default DemandeAdminCard;
