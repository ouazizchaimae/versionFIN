import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {RatioUniteDto}  from '../../../../../../controller/model/referentiel/RatioUnite.model';

type RatioUniteViewScreenRouteProp = RouteProp<{ RatioUniteDetails: { ratioUnite : RatioUniteDto } }, 'RatioUniteDetails'>;

type Props = { route: RatioUniteViewScreenRouteProp; };

const RatioUniteAdminView: React.FC<Props> = ({ route }) => {

    const { ratioUnite } = route.params;
    const [isRatioUniteCollapsed, setIsRatioUniteCollapsed] = useState(false);



    const ratioUniteCollapsible = () => {
        setIsRatioUniteCollapsed(!isRatioUniteCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={ratioUniteCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Ratio unite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isRatioUniteCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {ratioUnite.id}</Text>
                        <Text style={globalStyle.infos}>Entite: {ratioUnite?.entite?.libelle}</Text>
                        <Text style={globalStyle.infos}>Produit: {ratioUnite?.produit?.libelle}</Text>
                        <Text style={globalStyle.infos}>Ratio: {ratioUnite.ratio}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default RatioUniteAdminView;
