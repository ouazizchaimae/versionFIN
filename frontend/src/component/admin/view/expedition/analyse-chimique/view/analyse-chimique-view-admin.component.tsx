import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {AnalyseChimiqueDto}  from '../../../../../../controller/model/expedition/AnalyseChimique.model';

type AnalyseChimiqueViewScreenRouteProp = RouteProp<{ AnalyseChimiqueDetails: { analyseChimique : AnalyseChimiqueDto } }, 'AnalyseChimiqueDetails'>;

type Props = { route: AnalyseChimiqueViewScreenRouteProp; };

const AnalyseChimiqueAdminView: React.FC<Props> = ({ route }) => {

    const { analyseChimique } = route.params;
    const [isAnalyseChimiqueCollapsed, setIsAnalyseChimiqueCollapsed] = useState(false);

    const [isAnalyseChimiqueDetailsCollapsed, setIsAnalyseChimiqueDetailsCollapsed] = useState(true);

    const analyseChimiqueDetailsCollapsible = () => {
        setIsAnalyseChimiqueDetailsCollapsed(!isAnalyseChimiqueDetailsCollapsed);
    };

    const analyseChimiqueCollapsible = () => {
        setIsAnalyseChimiqueCollapsed(!isAnalyseChimiqueCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={analyseChimiqueCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Analyse chimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {analyseChimique.id}</Text>
                        <Text style={globalStyle.infos}>Code: {analyseChimique.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {analyseChimique.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {analyseChimique.description}</Text>
                        <Text style={globalStyle.infos}>Produit marchand: {analyseChimique?.produitMarchand?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>

            <TouchableOpacity onPress={analyseChimiqueDetailsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Analyse chimique details</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueDetailsCollapsed}>

                {analyseChimique.analyseChimiqueDetails && analyseChimique.analyseChimiqueDetails.length > 0 ? ( analyseChimique.analyseChimiqueDetails.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>Libelle : {item.libelle}</Text>
                            <Text style={globalStyle.infos}>Description : {item.description}</Text>
                            <Text style={globalStyle.infos}>Element chimique: {item?.elementChimique?.libelle}</Text>
                            <Text style={globalStyle.infos}>Valeur : {item.valeur}</Text>
                            <Text style={globalStyle.infos}>Conformite : {item.conformite}</Text>
                            <Text style={globalStyle.infos}>Surqualite : {item.surqualite}</Text>

                        </View>
                    </View>
                    )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No analyse chimique details</Text>
                    </View>
                )}

            </Collapsible>

        </ScrollView>

    </View>
);
};

export default AnalyseChimiqueAdminView;
