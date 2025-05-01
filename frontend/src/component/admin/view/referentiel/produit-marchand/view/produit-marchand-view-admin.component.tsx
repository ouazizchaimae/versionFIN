import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ProduitMarchandDto}  from '../../../../../../controller/model/referentiel/ProduitMarchand.model';

type ProduitMarchandViewScreenRouteProp = RouteProp<{ ProduitMarchandDetails: { produitMarchand : ProduitMarchandDto } }, 'ProduitMarchandDetails'>;

type Props = { route: ProduitMarchandViewScreenRouteProp; };

const ProduitMarchandAdminView: React.FC<Props> = ({ route }) => {

    const { produitMarchand } = route.params;
    const [isProduitMarchandCollapsed, setIsProduitMarchandCollapsed] = useState(false);



    const produitMarchandCollapsible = () => {
        setIsProduitMarchandCollapsed(!isProduitMarchandCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={produitMarchandCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Produit marchand</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isProduitMarchandCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {produitMarchand.id}</Text>
                        <Text style={globalStyle.infos}>Code: {produitMarchand.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {produitMarchand.libelle}</Text>
                        <Text style={globalStyle.infos}>Style: {produitMarchand.style}</Text>
                        <Text style={globalStyle.infos}>Description: {produitMarchand.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default ProduitMarchandAdminView;
