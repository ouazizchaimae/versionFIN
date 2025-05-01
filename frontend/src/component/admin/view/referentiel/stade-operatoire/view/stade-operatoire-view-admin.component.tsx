import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {StadeOperatoireDto}  from '../../../../../../controller/model/referentiel/StadeOperatoire.model';

type StadeOperatoireViewScreenRouteProp = RouteProp<{ StadeOperatoireDetails: { stadeOperatoire : StadeOperatoireDto } }, 'StadeOperatoireDetails'>;

type Props = { route: StadeOperatoireViewScreenRouteProp; };

const StadeOperatoireAdminView: React.FC<Props> = ({ route }) => {

    const { stadeOperatoire } = route.params;
    const [isStadeOperatoireCollapsed, setIsStadeOperatoireCollapsed] = useState(false);



    const stadeOperatoireCollapsible = () => {
        setIsStadeOperatoireCollapsed(!isStadeOperatoireCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={stadeOperatoireCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Stade operatoire</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isStadeOperatoireCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {stadeOperatoire.id}</Text>
                        <Text style={globalStyle.infos}>Code: {stadeOperatoire.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {stadeOperatoire.libelle}</Text>
                        <Text style={globalStyle.infos}>Style: {stadeOperatoire.style}</Text>
                        <Text style={globalStyle.infos}>Description: {stadeOperatoire.description}</Text>
                        <Text style={globalStyle.infos}>Capacite min: {stadeOperatoire.capaciteMin}</Text>
                        <Text style={globalStyle.infos}>Capacite max: {stadeOperatoire.capaciteMax}</Text>
                        <Text style={globalStyle.infos}>Indice: {stadeOperatoire.indice}</Text>
                        <Text style={globalStyle.infos}>Entite: {stadeOperatoire?.entite?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default StadeOperatoireAdminView;
