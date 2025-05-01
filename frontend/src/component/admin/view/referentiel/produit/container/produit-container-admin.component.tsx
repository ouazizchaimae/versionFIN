import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import ProduitAdminTabNavigation from '../../../../../../navigation/admin/referentiel/ProduitAdminTabNavigation';

const ProduitAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <ProduitAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default ProduitAdmin;
