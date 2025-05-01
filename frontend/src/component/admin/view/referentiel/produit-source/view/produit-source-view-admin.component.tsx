import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ProduitSourceDto}  from '../../../../../../controller/model/referentiel/ProduitSource.model';

type ProduitSourceViewScreenRouteProp = RouteProp<{ ProduitSourceDetails: { produitSource : ProduitSourceDto } }, 'ProduitSourceDetails'>;

type Props = { route: ProduitSourceViewScreenRouteProp; };

const ProduitSourceAdminView: React.FC<Props> = ({ route }) => {

    const { produitSource } = route.params;
    const [isProduitSourceCollapsed, setIsProduitSourceCollapsed] = useState(false);



    const produitSourceCollapsible = () => {
        setIsProduitSourceCollapsed(!isProduitSourceCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={produitSourceCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Produit source</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isProduitSourceCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {produitSource.id}</Text>
                        <Text style={globalStyle.infos}>Code: {produitSource.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {produitSource.libelle}</Text>
                        <Text style={globalStyle.infos}>Style: {produitSource.style}</Text>
                        <Text style={globalStyle.infos}>Description: {produitSource.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default ProduitSourceAdminView;
