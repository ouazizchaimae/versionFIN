import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {UniteDto} from '../../../../../../controller/model/referentiel/Unite.model';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import {StadeOperatoireDto} from '../../../../../../controller/model/referentiel/StadeOperatoire.model';
import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

    const SuiviProductionAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {
    const [showJourFromPicker, setShowJourFromPicker] = useState(false);
    const [showJourToPicker, setShowJourToPicker] = useState(false);

    const [produits, setProduits] = useState<ProduitDto[]>([]);
    const [stadeOperatoires, setStadeOperatoires] = useState<StadeOperatoireDto[]>([]);
    const [unites, setUnites] = useState<UniteDto[]>([]);


    const uniteService = new UniteAdminService();
    type UniteResponse = AxiosResponse<UniteDto[]>;
    const stadeOperatoireService = new StadeOperatoireAdminService();
    type StadeOperatoireResponse = AxiosResponse<StadeOperatoireDto[]>;
    const produitService = new ProduitAdminService();
    type ProduitResponse = AxiosResponse<ProduitDto[]>;

    const onJourFromChange = (event, selectedJour: Date) => {
        const jour = selectedJour || criteria.jourFrom;
        setShowJourFromPicker(false);
        setCriteria({ ...criteria, jourFrom: jour });
    };

    const onJourToChange = (event, selectedJour: Date) => {
        const jour = selectedJour || criteria.jourTo;
        setShowJourToPicker(false);
        setCriteria({ ...criteria, jourTo: jour });
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
    const findStadeOperatoire = async () => {
        try {
            const [response] = await Promise.all<StadeOperatoireResponse>([
            stadeOperatoireService.getList(),
            ]);
            setStadeOperatoires(response.data);
        } catch (error) {
            console.error(error);
        }
    };
    const findUnite = async () => {
        try {
            const [response] = await Promise.all<UniteResponse>([
            uniteService.getList(),
            ]);
            setUnites(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            findProduit();
            findStadeOperatoire();
            findUnite();
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
                        <Text style={styles.label}>Jour From</Text>
                        <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowJourFromPicker(true)} >
                            <Text>
                                {criteria.jourFrom ? criteria.jourFrom.toISOString().slice(0, 10) : 'Select Jour'}
                            </Text>
                            <Ionicons name="calendar-outline" size={24} color="grey" />
                        </TouchableOpacity>
                    </View>

                        {showJourFromPicker && (
                        <DateTimePicker value={criteria.JourFrom || new Date()} mode="date" display="default" onChange={onJourFromChange}/>
                        )}

                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Jour To</Text>
                            <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowJourToPicker(true)} >
                                <Text>
                                    {criteria.jourTo ? criteria.jourTo.toISOString().slice(0, 10) : 'Select Jour'}
                                </Text>
                                <Ionicons name="calendar-outline" size={24} color="grey" />
                            </TouchableOpacity>
                        </View>

                        {showJourToPicker && (
                        <DateTimePicker value={criteria.JourTo || new Date()} mode="date" display="default" onChange={onJourToChange}/>
                                )}

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Volume Min</Text>
                        <TextInput style={styles.input} placeholder="Volume Min" keyboardType="numeric"
                                value={criteria.volumeMin !== null ? String(criteria.volumeMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, volumeMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Volume Max</Text>
                        <TextInput style={styles.input} placeholder="Volume Max" keyboardType="numeric"
                                   value={criteria.volumeMax !== null ? String(criteria.volumeMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, volumeMax: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Tsm Min</Text>
                        <TextInput style={styles.input} placeholder="Tsm Min" keyboardType="numeric"
                                value={criteria.tsmMin !== null ? String(criteria.tsmMin) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, tsmMin: parseFloat(text) })} />
                    </View>

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Tsm Max</Text>
                        <TextInput style={styles.input} placeholder="Tsm Max" keyboardType="numeric"
                                   value={criteria.tsmMax !== null ? String(criteria.tsmMax) : ''}
                        onChangeText={(text) => setCriteria({ ...criteria, tsmMax: parseFloat(text) })} />
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
                            <Text style={styles.label}>StadeOperatoire</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.stadeOperatoire?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = stadeOperatoires.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, stadeOperatoire: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Stade operatoire" value="" />
                                    {stadeOperatoires.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Unite</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.unite?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = unites.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, unite: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Unite" value="" />
                                    {unites.map((item) => (
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

export default SuiviProductionAdminSearchModal;
