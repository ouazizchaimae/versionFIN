import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {EntiteDto}  from '../../../../../../controller/model/referentiel/Entite.model';

type EntiteViewScreenRouteProp = RouteProp<{ EntiteDetails: { entite : EntiteDto } }, 'EntiteDetails'>;

type Props = { route: EntiteViewScreenRouteProp; };

const EntiteAdminView: React.FC<Props> = ({ route }) => {

    const { entite } = route.params;
    const [isEntiteCollapsed, setIsEntiteCollapsed] = useState(false);



    const entiteCollapsible = () => {
        setIsEntiteCollapsed(!isEntiteCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={entiteCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Entite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isEntiteCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {entite.id}</Text>
                        <Text style={globalStyle.infos}>Code: {entite.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {entite.libelle}</Text>
                        <Text style={globalStyle.infos}>Style: {entite.style}</Text>
                        <Text style={globalStyle.infos}>Description: {entite.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default EntiteAdminView;
