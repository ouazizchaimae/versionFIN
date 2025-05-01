import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

    const ExpeditionProduitAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {

    const [analyseChimiques, setAnalyseChimiques] = useState<AnalyseChimiqueDto[]>([]);
    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);


    const analyseChimiqueService = new AnalyseChimiqueAdminService();
    type AnalyseChimiqueResponse = AxiosResponse<AnalyseChimiqueDto[]>;
    const charteChimiqueService = new CharteChimiqueAdminService();
    type CharteChimiqueResponse = AxiosResponse<CharteChimiqueDto[]>;


    const findAnalyseChimique = async () => {
        try {
            const [response] = await Promise.all<AnalyseChimiqueResponse>([
            analyseChimiqueService.getList(),
            ]);
            setAnalyseChimiques(response.data);
        } catch (error) {
            console.error(error);
        }
    };
    const findCharteChimique = async () => {
        try {
            const [response] = await Promise.all<CharteChimiqueResponse>([
            charteChimiqueService.getList(),
            ]);
            setCharteChimiques(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            findAnalyseChimique();
            findCharteChimique();
        }, [])
    );

    return (
        <Modal visible={isVisible} animationType="slide" transparent>
            <View style={styles.modalBackground}>
                <View style={styles.modalContainer}>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Code</Text>
                        <TextInput style={styles.input} placeholder="Code" value={criteria.codeLike}
                                onChangeText={(text) => setCriteria({ ...criteria, codeLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Libelle</Text>
                        <TextInput style={styles.input} placeholder="Libelle" value={criteria.libelleLike}
                                onChangeText={(text) => setCriteria({ ...criteria, libelleLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Description</Text>
                        <TextInput style={styles.input} placeholder="Description" value={criteria.descriptionLike}
                                onChangeText={(text) => setCriteria({ ...criteria, descriptionLike: text })}/>
                    </View>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>AnalyseChimique</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.analyseChimique?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = analyseChimiques.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, analyseChimique: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Analyse chimique" value="" />
                                    {analyseChimiques.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>CharteChimique</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.charteChimique?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = charteChimiques.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, charteChimique: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Charte chimique" value="" />
                                    {charteChimiques.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
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

export default ExpeditionProduitAdminSearchModal;
