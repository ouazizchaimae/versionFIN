import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {CharteChimiqueDto}  from '../../../../../../controller/model/expedition/CharteChimique.model';

type CharteChimiqueViewScreenRouteProp = RouteProp<{ CharteChimiqueDetails: { charteChimique : CharteChimiqueDto } }, 'CharteChimiqueDetails'>;

type Props = { route: CharteChimiqueViewScreenRouteProp; };

const CharteChimiqueAdminView: React.FC<Props> = ({ route }) => {

    const { charteChimique } = route.params;
    const [isCharteChimiqueCollapsed, setIsCharteChimiqueCollapsed] = useState(false);

    const [isCharteChimiqueDetailsCollapsed, setIsCharteChimiqueDetailsCollapsed] = useState(true);

    const charteChimiqueDetailsCollapsible = () => {
        setIsCharteChimiqueDetailsCollapsed(!isCharteChimiqueDetailsCollapsed);
    };

    const charteChimiqueCollapsible = () => {
        setIsCharteChimiqueCollapsed(!isCharteChimiqueCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={charteChimiqueCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Charte chimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {charteChimique.id}</Text>
                        <Text style={globalStyle.infos}>Code: {charteChimique.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {charteChimique.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {charteChimique.description}</Text>
                        <Text style={globalStyle.infos}>Client: {charteChimique?.client?.libelle}</Text>
                        <Text style={globalStyle.infos}>Produit marchand: {charteChimique?.produitMarchand?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>

            <TouchableOpacity onPress={charteChimiqueDetailsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Charte chimique details</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueDetailsCollapsed}>

                {charteChimique.charteChimiqueDetails && charteChimique.charteChimiqueDetails.length > 0 ? ( charteChimique.charteChimiqueDetails.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>Libelle : {item.libelle}</Text>
                            <Text style={globalStyle.infos}>Description : {item.description}</Text>
                            <Text style={globalStyle.infos}>Element chimique: {item?.elementChimique?.libelle}</Text>
                            <Text style={globalStyle.infos}>Minimum : {item.minimum}</Text>
                            <Text style={globalStyle.infos}>Maximum : {item.maximum}</Text>
                            <Text style={globalStyle.infos}>Average : {item.average}</Text>
                            <Text style={globalStyle.infos}>Methode analyse : {item.methodeAnalyse}</Text>
                            <Text style={globalStyle.infos}>Unite : {item.unite}</Text>

                        </View>
                    </View>
                    )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No charte chimique details</Text>
                    </View>
                )}

            </Collapsible>

        </ScrollView>

    </View>
);
};

export default CharteChimiqueAdminView;
