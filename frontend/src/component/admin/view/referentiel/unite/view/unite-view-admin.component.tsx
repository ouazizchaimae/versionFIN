import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {UniteDto}  from '../../../../../../controller/model/referentiel/Unite.model';

type UniteViewScreenRouteProp = RouteProp<{ UniteDetails: { unite : UniteDto } }, 'UniteDetails'>;

type Props = { route: UniteViewScreenRouteProp; };

const UniteAdminView: React.FC<Props> = ({ route }) => {

    const { unite } = route.params;
    const [isUniteCollapsed, setIsUniteCollapsed] = useState(false);



    const uniteCollapsible = () => {
        setIsUniteCollapsed(!isUniteCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={uniteCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Unite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isUniteCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {unite.id}</Text>
                        <Text style={globalStyle.infos}>Code: {unite.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {unite.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {unite.description}</Text>
                        <Text style={globalStyle.infos}>Style: {unite.style}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default UniteAdminView;
