import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {DemandeDto}  from '../../../../../../controller/model/demande/Demande.model';

type DemandeViewScreenRouteProp = RouteProp<{ DemandeDetails: { demande : DemandeDto } }, 'DemandeDetails'>;

type Props = { route: DemandeViewScreenRouteProp; };

const DemandeAdminView: React.FC<Props> = ({ route }) => {

    const { demande } = route.params;
    const [isDemandeCollapsed, setIsDemandeCollapsed] = useState(false);



    const demandeCollapsible = () => {
        setIsDemandeCollapsed(!isDemandeCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={demandeCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Demande</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isDemandeCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {demande.id}</Text>
                        <Text style={globalStyle.infos}>Code: {demande.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {demande.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {demande.description}</Text>
                        <Text style={globalStyle.infos}>Produit marchand: {demande?.produitMarchand?.libelle}</Text>
                        <Text style={globalStyle.infos}>Client: {demande?.client?.libelle}</Text>
                        <Text style={globalStyle.infos}>Date demande: {demande.dateDemande}</Text>
                        <Text style={globalStyle.infos}>Date expedition: {demande.dateExpedition}</Text>
                        <Text style={globalStyle.infos}>Volume: {demande.volume}</Text>
                        <Text style={globalStyle.infos}>Type demande: {demande?.typeDemande?.libelle}</Text>
                        <Text style={globalStyle.infos}>Etat demande: {demande?.etatDemande?.libelle}</Text>
                        <Text style={globalStyle.infos}>Action entreprise: {demande.actionEntreprise}</Text>
                        <Text style={globalStyle.infos}>Trg: {demande.trg}</Text>
                        <Text style={globalStyle.infos}>Cause: {demande.cause}</Text>
                        <Text style={globalStyle.infos}>Commentaire: {demande.commentaire}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default DemandeAdminView;
