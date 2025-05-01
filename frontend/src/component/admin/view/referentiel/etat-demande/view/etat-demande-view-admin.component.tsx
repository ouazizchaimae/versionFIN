import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {EtatDemandeDto}  from '../../../../../../controller/model/referentiel/EtatDemande.model';

type EtatDemandeViewScreenRouteProp = RouteProp<{ EtatDemandeDetails: { etatDemande : EtatDemandeDto } }, 'EtatDemandeDetails'>;

type Props = { route: EtatDemandeViewScreenRouteProp; };

const EtatDemandeAdminView: React.FC<Props> = ({ route }) => {

    const { etatDemande } = route.params;
    const [isEtatDemandeCollapsed, setIsEtatDemandeCollapsed] = useState(false);



    const etatDemandeCollapsible = () => {
        setIsEtatDemandeCollapsed(!isEtatDemandeCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={etatDemandeCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Etat demande</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isEtatDemandeCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {etatDemande.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {etatDemande.libelle}</Text>
                        <Text style={globalStyle.infos}>Code: {etatDemande.code}</Text>
                        <Text style={globalStyle.infos}>Style: {etatDemande.style}</Text>
                        <Text style={globalStyle.infos}>Description: {etatDemande.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default EtatDemandeAdminView;
