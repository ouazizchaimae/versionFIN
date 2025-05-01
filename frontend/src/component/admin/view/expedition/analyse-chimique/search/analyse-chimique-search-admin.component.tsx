import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {AnalyseChimiqueDetailDto} from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';
import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';

    const AnalyseChimiqueAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {

    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);


    const analyseChimiqueDetailService = new AnalyseChimiqueDetailAdminService();
    type AnalyseChimiqueDetailResponse = AxiosResponse<AnalyseChimiqueDetailDto[]>;
    const elementChimiqueService = new ElementChimiqueAdminService();
    type ElementChimiqueResponse = AxiosResponse<ElementChimiqueDto[]>;
    const produitMarchandService = new ProduitMarchandAdminService();
    type ProduitMarchandResponse = AxiosResponse<ProduitMarchandDto[]>;


    const findProduitMarchand = async () => {
        try {
            const [response] = await Promise.all<ProduitMarchandResponse>([
            produitMarchandService.getList(),
            ]);
            setProduitMarchands(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            findProduitMarchand();
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
                            <Text style={styles.label}>ProduitMarchand</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.produitMarchand?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = produitMarchands.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, produitMarchand: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Produit marchand" value="" />
                                    {produitMarchands.map((item) => (
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

export default AnalyseChimiqueAdminSearchModal;
