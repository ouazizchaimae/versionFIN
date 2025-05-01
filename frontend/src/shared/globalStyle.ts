import {StyleSheet} from "react-native";

export const globalStyle = StyleSheet.create({

    myContainer: {flex: 1},
    //the card Style
    card: {
        flexDirection: 'column',
        marginHorizontal: 10,
        borderRadius: 15,
        marginVertical: 10,
        elevation: 3,
        backgroundColor: '#FFF',
        paddingHorizontal: 15,
        paddingVertical: 10,
        shadowColor: '#000',
        shadowOffset: {width: 0, height: 2},
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    contentContainer: {
        paddingBottom: 10,
    },
    infos: {
        flexDirection: 'row',
        marginBottom: 5,
    },
    label: {
        fontSize: 16,
        fontWeight: 'bold',
        color: '#333',
        marginRight: 5,
    },
    value: {
        fontSize: 16,
        color: '#666',
    },
    buttonsContainer: {
        flexDirection: 'row',
        justifyContent: 'flex-end',
    },
    button: {
        marginLeft: 10,
    },
    //create style
    container: {
        backgroundColor: '#f5f5f5',
        width: '100%',
        borderColor: '#e8e8e8',
        borderWidth: 1,
        borderRadius: 7,
        //paddingHorizontal: 5,
        marginTop: 15,
        marginBottom: 10
    },

    input: {
        height: 50,
    },

    modalBackground: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
    },

    modalContent: {
        backgroundColor: 'white',
        padding: 20,
        borderRadius: 8,
        flexDirection: 'row'
    },

    modalText: {
        fontSize: 18,
        fontWeight: 'bold',
        textAlign: 'center',
        marginHorizontal: 10
    },

    itemInput: {
        backgroundColor: '#f5f5f5',
        width: '100%',
        borderColor: '#e8e8e8',
        borderWidth: 1,
        borderRadius: 7,
        paddingHorizontal: 15,
        marginTop: 15,
        height: 50,
    },

    infos_create: {
        flexDirection: 'row',
        alignItems: 'baseline',
        marginVertical: 6.5,
        fontSize: 15,
        fontWeight: 'bold'
    },

    itemCard: {
        marginVertical: 5,
        backgroundColor: '#f8f8ff',
        borderRadius: 10,
        padding: 10,
        flexDirection: 'row',
        justifyContent: 'space-between'
    },

    placeHolder: {
        backgroundColor: '#f5f5f5',
        width: '100%',
        borderColor: '#e8e8e8',
        borderWidth: 1,
        borderRadius: 7,
        paddingHorizontal: 15,
        padding: 15,
        marginTop: 15,
    },
    //view style
    infos_view: {
        flexDirection: 'row',
        alignItems: 'baseline',
        marginVertical: 6.5,
        fontSize: 15,
        fontWeight: 'bold',
    },

    itemCard_view: {
        marginVertical: 5,
        backgroundColor: '#f8f8ff',
        borderRadius: 10,
        padding: 10,
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
    searchContainer: {
        margin: 10,
        alignItems: 'center',
    },
    searchButton: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: '#4CAF50',
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
    },
    resetSearchButton: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'black',
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
    },
    searchText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        marginRight: 10,
    },
    headerContainer: {
        justifyContent: 'space-around',
        flexDirection: 'row',
        marginTop: 10
    },


    safeAreaViewCreate: {flex: 1, backgroundColor: '#e6e8fa'},
    scrolllViewCreate: {margin: 20, marginBottom: 80},
    textHeaderCreate: {fontSize: 30, fontWeight: 'bold', alignSelf: 'center', marginBottom: 10},
    touchableOpacityCreate: {backgroundColor: 'orange', padding: 10, borderRadius: 10, marginVertical: 5},
    touchableOpacityButtonCreate: {textAlign: 'center', fontWeight: 'bold', fontSize: 20},

    safeAreaViewEdit: {flex: 1, backgroundColor: '#e6e8fa'},
    scrolllViewEdit: {margin: 20, marginBottom: 80},
    textHeaderEdit: {fontSize: 30, fontWeight: 'bold', alignSelf: 'center', marginBottom: 10},
    touchableOpacityEdit: {backgroundColor: 'orange', padding: 10, borderRadius: 10, marginVertical: 5},
    touchableOpacityButtonEdit: {textAlign: 'center', fontWeight: 'bold', fontSize: 20},

    safeAreaViewView: {flex: 1, backgroundColor: '#e6e8fa'},
    scrolllViewView: {margin: 20, marginBottom: 80},
    textHeaderView: {fontSize: 30, fontWeight: 'bold', alignSelf: 'center', marginBottom: 10},
    touchableOpacityView: {backgroundColor: 'orange', padding: 10, borderRadius: 10, marginVertical: 5},
    touchableOpacityButtonView: {textAlign: 'center', fontWeight: 'bold', fontSize: 20},

    scrollViewList: {paddingHorizontal: 10, backgroundColor: '#e6e8fa'},
    textHeaderList: {fontSize: 30, fontWeight: 'bold', alignSelf: 'center', marginVertical: 10,},
    textNotFound: {fontSize: 20, textAlign: 'center', color: 'red', marginTop: 20},

    viewCreate: {flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'},
    viewEdit: {flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'},
    touchableOpacityYellow: {backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5},
    touchableOpacityGreen: {
        backgroundColor: '#32cd32',
        borderRadius: 10,
        marginBottom: 5,
        width: '20%',
        paddingVertical: 10,
        marginLeft: '80%',
        marginTop: 10
    },
    viewIcon: {alignItems: 'center', flexDirection: 'column', justifyContent: 'space-between'}
})
