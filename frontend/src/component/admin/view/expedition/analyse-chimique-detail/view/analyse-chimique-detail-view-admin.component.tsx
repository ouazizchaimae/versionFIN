import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {AnalyseChimiqueDetailDto}  from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';

type AnalyseChimiqueDetailViewScreenRouteProp = RouteProp<{ AnalyseChimiqueDetailDetails: { analyseChimiqueDetail : AnalyseChimiqueDetailDto } }, 'AnalyseChimiqueDetailDetails'>;

type Props = { route: AnalyseChimiqueDetailViewScreenRouteProp; };

const AnalyseChimiqueDetailAdminView: React.FC<Props> = ({ route }) => {

    const { analyseChimiqueDetail } = route.params;
    const [isAnalyseChimiqueDetailCollapsed, setIsAnalyseChimiqueDetailCollapsed] = useState(false);



    const analyseChimiqueDetailCollapsible = () => {
        setIsAnalyseChimiqueDetailCollapsed(!isAnalyseChimiqueDetailCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={analyseChimiqueDetailCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Analyse chimique detail</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueDetailCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {analyseChimiqueDetail.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {analyseChimiqueDetail.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {analyseChimiqueDetail.description}</Text>
                        <Text style={globalStyle.infos}>Element chimique: {analyseChimiqueDetail?.elementChimique?.libelle}</Text>
                        <Text style={globalStyle.infos}>Valeur: {analyseChimiqueDetail.valeur}</Text>
                        <Text style={globalStyle.infos}>Conformite: {analyseChimiqueDetail.conformite}</Text>
                        <Text style={globalStyle.infos}>Surqualite: {analyseChimiqueDetail.surqualite}</Text>
                        <Text style={globalStyle.infos}>Analyse chimique: {analyseChimiqueDetail?.analyseChimique?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default AnalyseChimiqueDetailAdminView;
