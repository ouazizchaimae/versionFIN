import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import ClientAdminTabNavigation from '../../../../../../navigation/admin/referentiel/ClientAdminTabNavigation';

const ClientAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <ClientAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default ClientAdmin;
