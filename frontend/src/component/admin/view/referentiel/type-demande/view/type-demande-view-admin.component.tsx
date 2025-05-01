import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {TypeDemandeDto}  from '../../../../../../controller/model/referentiel/TypeDemande.model';

type TypeDemandeViewScreenRouteProp = RouteProp<{ TypeDemandeDetails: { typeDemande : TypeDemandeDto } }, 'TypeDemandeDetails'>;

type Props = { route: TypeDemandeViewScreenRouteProp; };

const TypeDemandeAdminView: React.FC<Props> = ({ route }) => {

    const { typeDemande } = route.params;
    const [isTypeDemandeCollapsed, setIsTypeDemandeCollapsed] = useState(false);



    const typeDemandeCollapsible = () => {
        setIsTypeDemandeCollapsed(!isTypeDemandeCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={typeDemandeCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Type demande</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isTypeDemandeCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {typeDemande.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {typeDemande.libelle}</Text>
                        <Text style={globalStyle.infos}>Code: {typeDemande.code}</Text>
                        <Text style={globalStyle.infos}>Style: {typeDemande.style}</Text>
                        <Text style={globalStyle.infos}>Description: {typeDemande.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default TypeDemandeAdminView;
