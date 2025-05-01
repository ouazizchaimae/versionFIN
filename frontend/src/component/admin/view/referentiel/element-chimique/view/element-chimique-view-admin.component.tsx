import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ElementChimiqueDto}  from '../../../../../../controller/model/referentiel/ElementChimique.model';

type ElementChimiqueViewScreenRouteProp = RouteProp<{ ElementChimiqueDetails: { elementChimique : ElementChimiqueDto } }, 'ElementChimiqueDetails'>;

type Props = { route: ElementChimiqueViewScreenRouteProp; };

const ElementChimiqueAdminView: React.FC<Props> = ({ route }) => {

    const { elementChimique } = route.params;
    const [isElementChimiqueCollapsed, setIsElementChimiqueCollapsed] = useState(false);



    const elementChimiqueCollapsible = () => {
        setIsElementChimiqueCollapsed(!isElementChimiqueCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={elementChimiqueCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Element chimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isElementChimiqueCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {elementChimique.id}</Text>
                        <Text style={globalStyle.infos}>Code: {elementChimique.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {elementChimique.libelle}</Text>
                        <Text style={globalStyle.infos}>Style: {elementChimique.style}</Text>
                        <Text style={globalStyle.infos}>Description: {elementChimique.description}</Text>
                        <Text style={globalStyle.infos}>Unite: {elementChimique?.unite?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default ElementChimiqueAdminView;
