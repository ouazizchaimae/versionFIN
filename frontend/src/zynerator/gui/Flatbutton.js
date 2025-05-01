import { Image, StyleSheet, Text,View,TouchableOpacity} from "react-native";


export default function FlatButton({text,onPress,disabled}){
    return(
        <TouchableOpacity onPress={onPress} disabled={disabled}>
        <View  style={styles.button}>
        <Text  style={styles.buttonText} >{text}</Text>
        </View>
        </TouchableOpacity>
    )
};

const styles=StyleSheet.create({
    button:{
        borderRadius: 8,
        paddingVertical: 14,
        paddingHorizontal:10,
        backgroundColor: '#f2d647'

    },
    buttonText:{
        color:'white',
        fontWeight: 'bold',
        textTransform: 'uppercase',
        fontSize: 16,
        textAlign: 'center'
    }
})