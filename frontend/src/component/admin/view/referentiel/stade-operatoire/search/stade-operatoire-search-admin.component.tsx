import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {StadeOperatoireProduitDto} from '../../../../../../controller/model/referentiel/StadeOperatoireProduit.model';
import {StadeOperatoireProduitAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireProduitAdminService.service';
import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

    const StadeOperatoireAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {

    const [entites, setEntites] = useState<EntiteDto[]>([]);


    const stadeOperatoireProduitService = new StadeOperatoireProduitAdminService();
    type StadeOperatoireProduitResponse = AxiosResponse<StadeOperatoireProduitDto[]>;
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

    useFocusEffect(
        useCallback(() => {
            findEntite();
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
                        <Text style={styles.label}>Style</Text>
                        <TextInput style={styles.input} placeholder="Style" value={criteria.styleLike}
                                onChangeText={(text) => setCriteria({ ...criteria, styleLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Description</Text>
                        <TextInput style={styles.input} placeholder="Description" value={criteria.descriptionLike}
                                onChangeText={(text) => setCriteria({ ...criteria, descriptionLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Capacite min Min</Text>
                        <TextInput style={styles.input} placeholder="CapaciteMin Min" keyboardType="numeric"
                                value={criteria.capaciteMinMin !== null ? String(criteria.capaciteMinMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, capaciteMinMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Capacite min Max</Text>
                        <TextInput style={styles.input} placeholder="CapaciteMin Max" keyboardType="numeric"
                                   value={criteria.capaciteMinMax !== null ? String(criteria.capaciteMinMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, capaciteMinMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Capacite max Min</Text>
                        <TextInput style={styles.input} placeholder="CapaciteMax Min" keyboardType="numeric"
                                value={criteria.capaciteMaxMin !== null ? String(criteria.capaciteMaxMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, capaciteMaxMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Capacite max Max</Text>
                        <TextInput style={styles.input} placeholder="CapaciteMax Max" keyboardType="numeric"
                                   value={criteria.capaciteMaxMax !== null ? String(criteria.capaciteMaxMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, capaciteMaxMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Indice Min</Text>
                        <TextInput style={styles.input} placeholder="Indice Min" keyboardType="numeric"
                                value={criteria.indiceMin !== null ? String(criteria.indiceMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, indiceMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Indice Max</Text>
                        <TextInput style={styles.input} placeholder="Indice Max" keyboardType="numeric"
                                   value={criteria.indiceMax !== null ? String(criteria.indiceMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, indiceMax: parseFloat(text) })} />
                    </View>

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

export default StadeOperatoireAdminSearchModal;
