import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

    const RatioUniteAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {

    const [entites, setEntites] = useState<EntiteDto[]>([]);
    const [produits, setProduits] = useState<ProduitDto[]>([]);


    const entiteService = new EntiteAdminService();
    type EntiteResponse = AxiosResponse<EntiteDto[]>;
    const produitService = new ProduitAdminService();
    type ProduitResponse = AxiosResponse<ProduitDto[]>;


    const findEntite = async () => {
        try {
            const [response] = await Promise.all<EntiteResponse>([
            entiteService.getList(),
            ]);
            setEntites(response.data);
        } catch (error) {
            console.error(error);
        }
    };
    const findProduit = async () => {
        try {
            const [response] = await Promise.all<ProduitResponse>([
            produitService.getList(),
            ]);
            setProduits(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            findEntite();
            findProduit();
        }, [])
    );

    return (
        <Modal visible={isVisible} animationType="slide" transparent>
            <View style={styles.modalBackground}>
                <View style={styles.modalContainer}>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Entite</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.entite?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = entites.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, entite: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Entite" value="" />
                                    {entites.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Produit</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.produit?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = produits.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, produit: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Produit" value="" />
                                    {produits.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Ratio Min</Text>
                        <TextInput style={styles.input} placeholder="Ratio Min" keyboardType="numeric"
                                value={criteria.ratioMin !== null ? String(criteria.ratioMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, ratioMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Ratio Max</Text>
                        <TextInput style={styles.input} placeholder="Ratio Max" keyboardType="numeric"
                                   value={criteria.ratioMax !== null ? String(criteria.ratioMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, ratioMax: parseFloat(text) })} />
                    </View>


                    <View style={styles.buttonRow}>
                        <TouchableOpacity style={[styles.button, styles.closeButton]} onPress={handleClose}>
                            <Text style={styles.buttonText}>Close</Text>
                        </TouchableOpacity>

                        <TouchableOpacity style={[styles.button, styles.searchButton]} onPress={handleSearch}>
                            <Text style={styles.buttonText}>Validate</Text>
                        </TouchableOpacity>
                    </View>
                </View>
         </View>
       </Modal>
    );
};

const styles = StyleSheet.create({
    modalBackground: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'rgba(0,0,0,0.5)',
    },
    modalContainer: {
        backgroundColor: 'white',
        padding: 20,
        borderRadius: 10,
        width: '90%',
    },
    inputRow: {
        marginBottom: 15,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
    },
    label: {
        fontSize: 16,
        fontWeight: 'bold',
        color: '#333',
        flex: 1,
    },
    input: {
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        padding: 10,
        width: '70%',
        backgroundColor: '#f9f9f9',
    },
    datePickerButton: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        padding: 10,
        width: '70%',
        backgroundColor: '#f9f9f9',
    },
    pickerContainer: {
        flex: 2,
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        backgroundColor: '#f9f9f9',
    },
    picker: {
        height: 40,
        width: '100%',
    },
    buttonRow: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginTop: 20,
    },
    button: {
        padding: 10,
        borderRadius: 5,
        width: '45%',
        alignItems: 'center',
    },
    closeButton: {
        backgroundColor: '#f44336',
    },
    searchButton: {
        backgroundColor: '#4CAF50',
    },
    buttonText: {
        color: 'white',
        fontWeight: 'bold',
    },
});

export default RatioUniteAdminSearchModal;
