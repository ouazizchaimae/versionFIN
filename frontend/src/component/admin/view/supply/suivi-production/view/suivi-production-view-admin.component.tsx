import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {SuiviProductionDto}  from '../../../../../../controller/model/supply/SuiviProduction.model';

type SuiviProductionViewScreenRouteProp = RouteProp<{ SuiviProductionDetails: { suiviProduction : SuiviProductionDto } }, 'SuiviProductionDetails'>;

type Props = { route: SuiviProductionViewScreenRouteProp; };

const SuiviProductionAdminView: React.FC<Props> = ({ route }) => {

    const { suiviProduction } = route.params;
    const [isSuiviProductionCollapsed, setIsSuiviProductionCollapsed] = useState(false);



    const suiviProductionCollapsible = () => {
        setIsSuiviProductionCollapsed(!isSuiviProductionCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={suiviProductionCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Suivi production</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isSuiviProductionCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {suiviProduction.id}</Text>
                        <Text style={globalStyle.infos}>Code: {suiviProduction.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {suiviProduction.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {suiviProduction.description}</Text>
                        <Text style={globalStyle.infos}>Jour: {suiviProduction.jour}</Text>
                        <Text style={globalStyle.infos}>Volume: {suiviProduction.volume}</Text>
                        <Text style={globalStyle.infos}>Tsm: {suiviProduction.tsm}</Text>
                        <Text style={globalStyle.infos}>Produit: {suiviProduction?.produit?.libelle}</Text>
                        <Text style={globalStyle.infos}>Stade operatoire: {suiviProduction?.stadeOperatoire?.libelle}</Text>
                        <Text style={globalStyle.infos}>Unite: {suiviProduction?.unite?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default SuiviProductionAdminView;
