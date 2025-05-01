import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

    const CharteChimiqueDetailAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {

    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);


    const elementChimiqueService = new ElementChimiqueAdminService();
    type ElementChimiqueResponse = AxiosResponse<ElementChimiqueDto[]>;
    const charteChimiqueService = new CharteChimiqueAdminService();
    type CharteChimiqueResponse = AxiosResponse<CharteChimiqueDto[]>;


    const findElementChimique = async () => {
        try {
            const [response] = await Promise.all<ElementChimiqueResponse>([
            elementChimiqueService.getList(),
            ]);
            setElementChimiques(response.data);
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
            findElementChimique();
            findCharteChimique();
        }, [])
    );

    return (
        <Modal visible={isVisible} animationType="slide" transparent>
            <View style={styles.modalBackground}>
                <View style={styles.modalContainer}>
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
                            <Text style={styles.label}>ElementChimique</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.elementChimique?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = elementChimiques.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, elementChimique: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Element chimique" value="" />
                                    {elementChimiques.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Minimum Min</Text>
                        <TextInput style={styles.input} placeholder="Minimum Min" keyboardType="numeric"
                                value={criteria.minimumMin !== null ? String(criteria.minimumMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, minimumMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Minimum Max</Text>
                        <TextInput style={styles.input} placeholder="Minimum Max" keyboardType="numeric"
                                   value={criteria.minimumMax !== null ? String(criteria.minimumMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, minimumMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Maximum Min</Text>
                        <TextInput style={styles.input} placeholder="Maximum Min" keyboardType="numeric"
                                value={criteria.maximumMin !== null ? String(criteria.maximumMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, maximumMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Maximum Max</Text>
                        <TextInput style={styles.input} placeholder="Maximum Max" keyboardType="numeric"
                                   value={criteria.maximumMax !== null ? String(criteria.maximumMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, maximumMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Average Min</Text>
                        <TextInput style={styles.input} placeholder="Average Min" keyboardType="numeric"
                                value={criteria.averageMin !== null ? String(criteria.averageMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, averageMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Average Max</Text>
                        <TextInput style={styles.input} placeholder="Average Max" keyboardType="numeric"
                                   value={criteria.averageMax !== null ? String(criteria.averageMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, averageMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Methode analyse</Text>
                        <TextInput style={styles.input} placeholder="MethodeAnalyse" value={criteria.methodeAnalyseLike}
                                onChangeText={(text) => setCriteria({ ...criteria, methodeAnalyseLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Unite</Text>
                        <TextInput style={styles.input} placeholder="Unite" value={criteria.uniteLike}
                                onChangeText={(text) => setCriteria({ ...criteria, uniteLike: text })}/>
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

export default CharteChimiqueDetailAdminSearchModal;
