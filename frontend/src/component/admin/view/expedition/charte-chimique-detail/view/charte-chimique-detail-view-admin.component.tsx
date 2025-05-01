import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {CharteChimiqueDetailDto}  from '../../../../../../controller/model/expedition/CharteChimiqueDetail.model';

type CharteChimiqueDetailViewScreenRouteProp = RouteProp<{ CharteChimiqueDetailDetails: { charteChimiqueDetail : CharteChimiqueDetailDto } }, 'CharteChimiqueDetailDetails'>;

type Props = { route: CharteChimiqueDetailViewScreenRouteProp; };

const CharteChimiqueDetailAdminView: React.FC<Props> = ({ route }) => {

    const { charteChimiqueDetail } = route.params;
    const [isCharteChimiqueDetailCollapsed, setIsCharteChimiqueDetailCollapsed] = useState(false);



    const charteChimiqueDetailCollapsible = () => {
        setIsCharteChimiqueDetailCollapsed(!isCharteChimiqueDetailCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={charteChimiqueDetailCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Charte chimique detail</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueDetailCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {charteChimiqueDetail.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {charteChimiqueDetail.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {charteChimiqueDetail.description}</Text>
                        <Text style={globalStyle.infos}>Element chimique: {charteChimiqueDetail?.elementChimique?.libelle}</Text>
                        <Text style={globalStyle.infos}>Minimum: {charteChimiqueDetail.minimum}</Text>
                        <Text style={globalStyle.infos}>Maximum: {charteChimiqueDetail.maximum}</Text>
                        <Text style={globalStyle.infos}>Average: {charteChimiqueDetail.average}</Text>
                        <Text style={globalStyle.infos}>Methode analyse: {charteChimiqueDetail.methodeAnalyse}</Text>
                        <Text style={globalStyle.infos}>Unite: {charteChimiqueDetail.unite}</Text>
                        <Text style={globalStyle.infos}>Charte chimique: {charteChimiqueDetail?.charteChimique?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default CharteChimiqueDetailAdminView;
