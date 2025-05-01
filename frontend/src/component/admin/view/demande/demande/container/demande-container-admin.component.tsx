import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import DemandeAdminTabNavigation from '../../../../../../navigation/admin/demande/DemandeAdminTabNavigation';

const DemandeAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <DemandeAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default DemandeAdmin;
