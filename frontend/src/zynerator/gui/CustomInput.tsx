/*
import React from 'react';
import { View, Text, TextInput, StyleSheet } from 'react-native';
import { Controller } from 'react-hook-form';

const CustomInput = ({
    control,
    name,
    placeholder,
    keyboardT
}) => {
    return (
        <Controller
            control={control}
            rules={{ required: 'This field is required' }}
            render={({ field, fieldState: { error } }) => (
                <>
                    <View

                        style={
                            [
                                styles.container,
                                { borderColor: error ? 'red' : '#e8e8e8' },
                            ]}

                    >
                        <TextInput
                            placeholder={placeholder}
                            value={field.value === null ? '' : String(field.value)}
                            onChangeText={field.onChange}
                            style={[styles.input,
                            error && { borderColor: 'red' }
                            ]}
                            keyboardType={keyboardT}
                        />
                    </View>
                    {error && (
                        <Text style={{ color: 'red', alignSelf: 'stretch' }}>
                            {'Required'}
                        </Text>
                    )}


                </>
            )}
            name={name}
        />
    );
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#f5f5f5',
        width: '100%',
        borderColor: '#e8e8e8',
        borderWidth: 1,
        borderRadius: 7,
        paddingHorizontal: 15,
        marginTop: 15,
    },
    input: {
        height: 50,
    },
});

export default CustomInput;
*/

import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Switch, Pressable } from 'react-native';
import { Controller } from 'react-hook-form';
import DatePicker from 'react-native-datepicker';
//import 'react-datepicker/dist/react-datepicker.css';
import DateTimePicker from '@react-native-community/datetimepicker';
import Ionicons from 'react-native-vector-icons/Ionicons';

const CustomInput = ({
    control,
    name,
    placeholder,
    keyboardT,
    isBoolean = false,
    isDate = false,
    isTextArea = false,
    secureTextEntry = false
}) => {

    const [showDateFromPicker, setShowDateFromPicker] = useState(false); // State to control date picker visibility

    const formatDate = (date) => {
        if (!date) return placeholder;
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        return date.toLocaleDateString(undefined, options);
    };

    if (isBoolean) {
        return (
            <Controller
                control={control}
                render={({ field }) => (
                    <View style={styles.container}>
                        <Text>{placeholder}</Text>
                        <Switch value={field.value} onValueChange={field.onChange} />
                    </View>
                )}
                name={name}
            />
        );
    } else if (isDate) {
        return (
            <Controller
                control={control}
                render={({ field }) => (
                    <View style={styles.container}>
                        <Pressable 
                            onPress={() => setShowDateFromPicker(true)}
                            style={styles.datePickerButton}
                        >
                            <Text>{formatDate(field.value)}</Text>
                            <Ionicons name="calendar-outline" size={24} color="grey" />
                        </Pressable>
                        {showDateFromPicker && (
                            <DateTimePicker
                                value={field.value || new Date()}
                                mode="date"
                                display="default"
                                onChange={(event, selectedDate) => {
                                    setShowDateFromPicker(false);
                                    const currentDate = selectedDate || field.value;
                                    field.onChange(currentDate);
                                }}
                            />
                        )}
                    </View>
                )}
                name={name}
            />
        );
    } else if (isTextArea) {
        return (
            <Controller
                control={control}
                render={({ field }) => (
                    <View style={styles.container}>
                        <TextInput
                            placeholder={placeholder}
                            value={field.value}
                            onChangeText={field.onChange}
                            multiline={true}
                            style={[styles.input]}
                            keyboardType={keyboardT}
                        />
                    </View>
                )}
                name={name}
            />
        );
    } else {
        return (
            <Controller
                control={control}
                rules={{ required: 'This field is required' }}
                render={({ field, fieldState: { error } }) => (
                    <>
                        <View
                            style={[
                                styles.container,
                                { borderColor: error ? 'red' : '#e8e8e8' },
                            ]}
                        >
                            <TextInput
                                placeholder={placeholder}
                                value={field.value === null ? '' : String(field.value)}
                                onChangeText={field.onChange}
                                style={[
                                    styles.input,
                                    error && { borderColor: 'red' },
                                ]}
                                keyboardType={keyboardT}
                                secureTextEntry={secureTextEntry}
                            />
                        </View>
                        {error && (
                            <Text style={{ color: 'red', alignSelf: 'stretch' }}>
                                {'Required'}
                            </Text>
                        )}
                    </>
                )}
                name={name}
            />
        );
    }
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#f5f5f5',
        width: '100%',
        borderColor: '#e8e8e8',
        borderWidth: 1,
        borderRadius: 7,
        paddingHorizontal: 15,
        marginTop: 15,
    },
    input: {
        height: 50,
    },
    datePickerButton: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        //borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        padding: 10,
        width: '100%',
        backgroundColor: '#f9f9f9',
    },
});

export default CustomInput;

