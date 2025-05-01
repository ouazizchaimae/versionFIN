import React, { useState, useCallback } from 'react';
import { Modal, View, Text, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AxiosResponse } from 'axios';
import { useFocusEffect } from '@react-navigation/native';
import { Picker } from '@react-native-picker/picker';


import {EtatDemandeDto} from '../../../../../../controller/model/referentiel/EtatDemande.model';
import {EtatDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/EtatDemandeAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';
import {TypeDemandeDto} from '../../../../../../controller/model/referentiel/TypeDemande.model';
import {TypeDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/TypeDemandeAdminService.service';
import {ClientDto} from '../../../../../../controller/model/referentiel/Client.model';
import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';

    const DemandeAdminSearchModal = ({ isVisible, handleSearch, handleClose, criteria, setCriteria }) => {
    const [showDateDemandeFromPicker, setShowDateDemandeFromPicker] = useState(false);
    const [showDateDemandeToPicker, setShowDateDemandeToPicker] = useState(false);
    const [showDateExpeditionFromPicker, setShowDateExpeditionFromPicker] = useState(false);
    const [showDateExpeditionToPicker, setShowDateExpeditionToPicker] = useState(false);

    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const [clients, setClients] = useState<ClientDto[]>([]);
    const [typeDemandes, setTypeDemandes] = useState<TypeDemandeDto[]>([]);
    const [etatDemandes, setEtatDemandes] = useState<EtatDemandeDto[]>([]);


    const etatDemandeService = new EtatDemandeAdminService();
    type EtatDemandeResponse = AxiosResponse<EtatDemandeDto[]>;
    const produitMarchandService = new ProduitMarchandAdminService();
    type ProduitMarchandResponse = AxiosResponse<ProduitMarchandDto[]>;
    const typeDemandeService = new TypeDemandeAdminService();
    type TypeDemandeResponse = AxiosResponse<TypeDemandeDto[]>;
    const clientService = new ClientAdminService();
    type ClientResponse = AxiosResponse<ClientDto[]>;

    const onDateDemandeFromChange = (event, selectedDateDemande: Date) => {
        const dateDemande = selectedDateDemande || criteria.dateDemandeFrom;
        setShowDateDemandeFromPicker(false);
        setCriteria({ ...criteria, dateDemandeFrom: dateDemande });
    };

    const onDateDemandeToChange = (event, selectedDateDemande: Date) => {
        const dateDemande = selectedDateDemande || criteria.dateDemandeTo;
        setShowDateDemandeToPicker(false);
        setCriteria({ ...criteria, dateDemandeTo: dateDemande });
    };
    const onDateExpeditionFromChange = (event, selectedDateExpedition: Date) => {
        const dateExpedition = selectedDateExpedition || criteria.dateExpeditionFrom;
        setShowDateExpeditionFromPicker(false);
        setCriteria({ ...criteria, dateExpeditionFrom: dateExpedition });
    };

    const onDateExpeditionToChange = (event, selectedDateExpedition: Date) => {
        const dateExpedition = selectedDateExpedition || criteria.dateExpeditionTo;
        setShowDateExpeditionToPicker(false);
        setCriteria({ ...criteria, dateExpeditionTo: dateExpedition });
    };

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
    const findClient = async () => {
        try {
            const [response] = await Promise.all<ClientResponse>([
            clientService.getList(),
            ]);
            setClients(response.data);
        } catch (error) {
            console.error(error);
        }
    };
    const findTypeDemande = async () => {
        try {
            const [response] = await Promise.all<TypeDemandeResponse>([
            typeDemandeService.getList(),
            ]);
            setTypeDemandes(response.data);
        } catch (error) {
            console.error(error);
        }
    };
    const findEtatDemande = async () => {
        try {
            const [response] = await Promise.all<EtatDemandeResponse>([
            etatDemandeService.getList(),
            ]);
            setEtatDemandes(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            findProduitMarchand();
            findClient();
            findTypeDemande();
            findEtatDemande();
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
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Client</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.client?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = clients.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, client: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Client" value="" />
                                    {clients.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Date demande From</Text>
                        <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowDateDemandeFromPicker(true)} >
                            <Text>
                                {criteria.dateDemandeFrom ? criteria.dateDemandeFrom.toISOString().slice(0, 10) : 'Select DateDemande'}
                            </Text>
                            <Ionicons name="calendar-outline" size={24} color="grey" />
                        </TouchableOpacity>
                    </View>

                        {showDateDemandeFromPicker && (
                        <DateTimePicker value={criteria.DateDemandeFrom || new Date()} mode="date" display="default" onChange={onDateDemandeFromChange}/>
                        )}

                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Date demande To</Text>
                            <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowDateDemandeToPicker(true)} >
                                <Text>
                                    {criteria.dateDemandeTo ? criteria.dateDemandeTo.toISOString().slice(0, 10) : 'Select DateDemande'}
                                </Text>
                                <Ionicons name="calendar-outline" size={24} color="grey" />
                            </TouchableOpacity>
                        </View>

                        {showDateDemandeToPicker && (
                        <DateTimePicker value={criteria.DateDemandeTo || new Date()} mode="date" display="default" onChange={onDateDemandeToChange}/>
                                )}

                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Date expedition From</Text>
                        <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowDateExpeditionFromPicker(true)} >
                            <Text>
                                {criteria.dateExpeditionFrom ? criteria.dateExpeditionFrom.toISOString().slice(0, 10) : 'Select DateExpedition'}
                            </Text>
                            <Ionicons name="calendar-outline" size={24} color="grey" />
                        </TouchableOpacity>
                    </View>

                        {showDateExpeditionFromPicker && (
                        <DateTimePicker value={criteria.DateExpeditionFrom || new Date()} mode="date" display="default" onChange={onDateExpeditionFromChange}/>
                        )}

                        <View style={styles.inputRow}>
                            <Text style={styles.label}>Date expedition To</Text>
                            <TouchableOpacity style={styles.datePickerButton} onPress={() => setShowDateExpeditionToPicker(true)} >
                                <Text>
                                    {criteria.dateExpeditionTo ? criteria.dateExpeditionTo.toISOString().slice(0, 10) : 'Select DateExpedition'}
                                </Text>
                                <Ionicons name="calendar-outline" size={24} color="grey" />
                            </TouchableOpacity>
                        </View>

                        {showDateExpeditionToPicker && (
                        <DateTimePicker value={criteria.DateExpeditionTo || new Date()} mode="date" display="default" onChange={onDateExpeditionToChange}/>
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
                            <Text style={styles.label}>TypeDemande</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.typeDemande?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = typeDemandes.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, typeDemande: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Type demande" value="" />
                                    {typeDemandes.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                        <View style={styles.inputRow}>
                            <Text style={styles.label}>EtatDemande</Text>
                            <View style={styles.pickerContainer}>
                                <Picker selectedValue={criteria.etatDemande?.id || ''} style={styles.picker}
                                onValueChange={(itemValue) => {
                                    const selected = etatDemandes.find(element => element.id == itemValue);
                                    if (selected) {
                                        setCriteria({ ...criteria, etatDemande: selected });
                                    }
                                }}
                                >
                                <Picker.Item label="Select Etat demande" value="" />
                                    {etatDemandes.map((item) => (
                                        <Picker.Item key={item.id} label={item.libelle} value={item.id} />
                                    ))}
                                </Picker>
                            </View>
                        </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Action entreprise</Text>
                        <TextInput style={styles.input} placeholder="ActionEntreprise" value={criteria.actionEntrepriseLike}
                                onChangeText={(text) => setCriteria({ ...criteria, actionEntrepriseLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Trg</Text>
                        <TextInput style={styles.input} placeholder="Trg" value={criteria.trgLike}
                                onChangeText={(text) => setCriteria({ ...criteria, trgLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Cause</Text>
                        <TextInput style={styles.input} placeholder="Cause" value={criteria.causeLike}
                                onChangeText={(text) => setCriteria({ ...criteria, causeLike: text })}/>
                    </View>
                    <View style={styles.inputRow}>
                        <Text style={styles.label}>Commentaire</Text>
                        <TextInput style={styles.input} placeholder="Commentaire" value={criteria.commentaireLike}
                                onChangeText={(text) => setCriteria({ ...criteria, commentaireLike: text })}/>
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

export default DemandeAdminSearchModal;
